package {
	import flash.net.URLRequest;
    import flash.net.URLVariables;
	import flash.net.URLLoader;
	import flash.net.URLRequestMethod;
	import flash.net.URLLoaderDataFormat;
	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.events.MouseEvent;
	public class RodaLinguagem extends MovieClip {
		public static var _dataXML:XML;
		public static var _variables:URLVariables;
		public static var _url:String;
		
		public function enviarAcao(e:Event, faseJogo:String, tipoAcao:String, movimento:String, peca:String):void {
			var dataXML:XML =
				<action>
					<jogo>2</jogo>
					<jogador>lucio</jogador>
					<fasejogo>{faseJogo}</fasejogo>
					<tipoacao>{tipoAcao}</tipoacao>
					<movimento>
						<id>{movimento}</id>
						<peca>{peca}</peca>
					</movimento>
				</action>;
			
			_dataXML = dataXML;
			_url = "http://localhost:8080/PhidiasREST/acao";						
			
			sendPOST(e);
		}
		
		public function sendPOST(e:Event):void {
			var urlRequest:URLRequest=new URLRequest(_url);
			urlRequest.method = URLRequestMethod.POST;
			urlRequest.contentType = "text/xml"; 
			urlRequest.data = _dataXML.toXMLString(); 
		
			var loader:URLLoader = new URLLoader();			
			loader.addEventListener(Event.COMPLETE, handleServerResponse);
			loader.load(urlRequest);
		}
		
		public function handleServerResponse(e:Event):void {
			// handle server response here
			var xmlData:XML = new XML(e.target.data);
			trace(e.target.data);
			trace("Acao enviada: " + xmlData.valor); 			
		}
	}
}