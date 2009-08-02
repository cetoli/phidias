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
		public function sendData(e:Event):void {
			var url:String="http://localhost:8080/PhidiasREST/acao";

			var variables:URLVariables = new URLVariables();
			variables.userID=2;

			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.data=variables;

			var loader:URLLoader = new URLLoader();			
			loader.addEventListener(Event.COMPLETE, handleServerResponse);
			loader.load(urlRequest);
		}
		
		public function sendData2(e:Event):void {
			var url:String="http://localhost:8080/PhidiasREST/acao";

			var secondsUTC:Number = new Date().time; 
			var dataXML:XML =  
				<login> 
					<time>{secondsUTC}</time> 
					<username>Ernie</username> 
					<password>guru</password> 
				</login>; 

			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method = URLRequestMethod.POST;
			urlRequest.contentType = "text/xml"; 
			urlRequest.data = dataXML.toXMLString(); 

			var loader:URLLoader = new URLLoader();			
			//loader.dataFormat = URLLoaderDataFormat.TEXT;
			loader.addEventListener(Event.COMPLETE, handleServerResponse2);
			loader.load(urlRequest);
		}

		public function handleServerResponse(e:Event):void {
			// handle server response here
			var xmlData:XML = new XML(e.target.data);
			var patientList:XMLList = xmlData.paciente;

			//var  patientAttributes:XMLList  =  patientList.attributes();
			for each  (var  patient:XML  in patientList)  { 
				trace("Id: " + patient.attribute("id"));
				trace("Nome: " + patient.attribute("nome"));
			} 			
		}
		
		public function handleServerResponse2(e:Event):void {
			// handle server response here
			var xmlData:XML = new XML(e.target.data);
			trace("Resultado: " + xmlData.valor); 			
		}
	}
}