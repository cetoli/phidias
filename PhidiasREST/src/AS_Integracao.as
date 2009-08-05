package {
	import flash.net.URLRequest;
    import flash.net.URLVariables;
	import flash.net.URLLoader;
	import flash.net.URLRequestMethod;
	import flash.net.URLLoaderDataFormat;
	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.events.MouseEvent;
	public class Functions extends MovieClip {
		public static var _dataXML:XML;
		public static var _variables:URLVariables;
		public static var _url:String;
		
		public function sendGET(e:Event):void {
			//var url:String="http://localhost:8080/PhidiasREST/acao";

			//var variables:URLVariables = new URLVariables();
			//variables.userID=2;

			var urlRequest:URLRequest=new URLRequest(_url);
			urlRequest.data=_variables;

			var loader:URLLoader = new URLLoader();			
			loader.addEventListener(Event.COMPLETE, handleServerResponse);
			loader.load(urlRequest);
		}
		
		public function sendPOST(e:Event):void {
			var urlRequest:URLRequest=new URLRequest(_url);
			urlRequest.method = URLRequestMethod.POST;
			urlRequest.contentType = "text/xml"; 
			urlRequest.data = _dataXML.toXMLString(); 

			var loader:URLLoader = new URLLoader();			
			loader.addEventListener(Event.COMPLETE, handleServerResponsePOST);
			loader.load(urlRequest);
		}

		public function handleServerResponse(e:Event):void {
			var xmlData:XML = new XML(e.target.data);
			var patientList:XMLList = xmlData.paciente;

			//var  patientAttributes:XMLList  =  patientList.attributes();
			for each  (var  patient:XML  in patientList)  { 
				trace("Id: " + patient.attribute("id"));
				trace("Nome: " + patient.attribute("nome"));
			} 			
		}
		
		public function handleServerResponsePOST(e:Event):void {
			// handle server response here
			var xmlData:XML = new XML(e.target.data);
			trace("Resultado: " + xmlData.valor); 			
		}
	}
}