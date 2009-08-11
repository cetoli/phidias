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
		
		public function enviarAcao(faseJogo:String, tipoAcao:String, movimento:String, peca:String):void {
			var dataXML:XML =
				<action>
					<jogo>1</jogo>
					<jogador>jogadorflash</jogador>
					<fasejogo>{faseJogo}</fasejogo>
					<tipoacao>{tipoAcao}</tipoacao>
					<movimento>
						<id>{movimento}</id>
						<peca>{peca}</peca>
					</movimento>
				</action>;
			
			_dataXML = dataXML;
			_url = "http://www.baliu.com.br/PhidiasREST/acao";
			
			sendPOST();
		}
		
		public function criaSessao():void {
			var dataXML:XML =
				<sessao>
					<jogo>1</jogo>
					<jogador>jogadorflash</jogador>
				</sessao>;
			
			_dataXML = dataXML;
			_url = "http://www.baliu.com.br/PhidiasREST/sessao";						
			
			sendPOST();
		}
		
		public function sendPOST():void {
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
			trace("Acao enviada: " + xmlData.valor); 			
		}
	}
}