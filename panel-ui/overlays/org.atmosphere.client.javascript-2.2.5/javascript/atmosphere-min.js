(function(a,b){if(typeof define==="function"&&define.amd){define(b)
}else{a.atmosphere=b()
}}(this,function(){var c="2.2.5-javascript",a={},d,g=[],f=[],e=0,b=Object.prototype.hasOwnProperty;
a={onError:function(h){},onClose:function(h){},onOpen:function(h){},onReopen:function(h){},onMessage:function(h){},onReconnect:function(i,h){},onMessagePublished:function(h){},onTransportFailure:function(i,h){},onLocalMessage:function(h){},onFailureToReconnect:function(i,h){},onClientTimeout:function(h){},onOpenAfterResume:function(h){},WebsocketApiAdapter:function(i){var h,j;
i.onMessage=function(k){j.onmessage({data:k.responseBody})
};
i.onMessagePublished=function(k){j.onmessage({data:k.responseBody})
};
i.onOpen=function(k){j.onopen(k)
};
j={close:function(){h.close()
},send:function(k){h.push(k)
},onmessage:function(k){},onopen:function(k){},onclose:function(k){},onerror:function(k){}};
h=new a.subscribe(i);
return j
},AtmosphereRequest:function(Z){var p={timeout:300000,method:"GET",headers:{},contentType:"",callback:null,url:"",data:"",suspend:true,maxRequest:-1,reconnect:true,maxStreamingLength:10000000,lastIndex:0,logLevel:"info",requestCount:0,fallbackMethod:"GET",fallbackTransport:"streaming",transport:"long-polling",webSocketImpl:null,webSocketBinaryType:null,dispatchUrl:null,webSocketPathDelimiter:"@@",enableXDR:false,rewriteURL:false,attachHeadersAsQueryString:true,executeCallbackBeforeReconnect:false,readyState:0,withCredentials:false,trackMessageLength:false,messageDelimiter:"|",connectTimeout:-1,reconnectInterval:0,dropHeaders:true,uuid:0,async:true,shared:false,readResponsesHeaders:false,maxReconnectOnClose:5,enableProtocol:true,pollingInterval:0,heartbeat:{client:null,server:null},ackInterval:0,closeAsync:false,reconnectOnServerError:true,onError:function(aD){},onClose:function(aD){},onOpen:function(aD){},onMessage:function(aD){},onReopen:function(aE,aD){},onReconnect:function(aE,aD){},onMessagePublished:function(aD){},onTransportFailure:function(aE,aD){},onLocalMessage:function(aD){},onFailureToReconnect:function(aE,aD){},onClientTimeout:function(aD){},onOpenAfterResume:function(aD){}};
var ao={status:200,reasonPhrase:"OK",responseBody:"",messages:[],headers:[],state:"messageReceived",transport:"polling",error:null,request:null,partialMessage:"",errorHandled:false,closedByClientTimeout:false,ffTryingReconnect:false};
var at=null;
var ac=null;
var y=null;
var n=null;
var U=null;
var u=true;
var av=0;
var ag=" ";
var al=false;
var N=null;
var h;
var au=null;
var O=a.util.now();
var x;
var aC;
ak(Z);
function af(){u=true;
al=false;
av=0;
at=null;
ac=null;
y=null;
n=null
}function R(){k();
af()
}function G(aE,aD){if(ao.partialMessage===""&&(aD.transport==="streaming")&&(aE.responseText.length>aD.maxStreamingLength)){return true
}return false
}function B(){if(p.enableProtocol&&!p.firstMessage){var aF="X-Atmosphere-Transport=close&X-Atmosphere-tracking-id="+p.uuid;
a.util.each(p.headers,function(aH,aJ){var aI=a.util.isFunction(aJ)?aJ.call(this,p,p,ao):aJ;
if(aI!=null){aF+="&"+encodeURIComponent(aH)+"="+encodeURIComponent(aI)
}});
var aD=p.url.replace(/([?&])_=[^&]*/,aF);
aD=aD+(aD===p.url?(/\?/.test(p.url)?"&":"?")+aF:"");
var aE={connected:false};
var aG=new a.AtmosphereRequest(aE);
aG.attachHeadersAsQueryString=false;
aG.dropHeaders=true;
aG.url=aD;
aG.contentType="text/plain";
aG.transport="polling";
aG.method="GET";
aG.data="";
if(p.enableXDR){aG.enableXDR=p.enableXDR
}aG.async=aE.closeAsync;
ai("",aG)
}}function F(){if(p.logLevel==="debug"){a.util.debug("Closing")
}al=true;
if(p.reconnectId){clearTimeout(p.reconnectId);
delete p.reconnectId
}if(p.heartbeatTimer){clearTimeout(p.heartbeatTimer)
}p.reconnect=false;
ao.request=p;
ao.state="unsubscribe";
ao.responseBody="";
ao.status=408;
ao.partialMessage="";
ae();
B();
k()
}function k(){ao.partialMessage="";
if(p.id){clearTimeout(p.id)
}if(p.heartbeatTimer){clearTimeout(p.heartbeatTimer)
}if(n!=null){n.close();
n=null
}if(U!=null){U.abort();
U=null
}if(y!=null){y.abort();
y=null
}if(at!=null){if(at.canSendMessage){at.close()
}at=null
}if(ac!=null){ac.close();
ac=null
}ad()
}function ad(){if(h!=null){clearInterval(x);
document.cookie=aC+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
h.signal("close",{reason:"",heir:!al?O:(h.get("children")||[])[0]});
h.close()
}if(au!=null){au.close()
}}function ak(aD){R();
p=a.util.extend(p,aD);
p.mrequest=p.reconnect;
if(!p.reconnect){p.reconnect=true
}}function aq(){return p.webSocketImpl!=null||window.WebSocket||window.MozWebSocket
}function ap(){return window.EventSource
}function W(){if(p.shared){au=aA(p);
if(au!=null){if(p.logLevel==="debug"){a.util.debug("Storage service available. All communication will be local")
}if(au.open(p)){return
}}if(p.logLevel==="debug"){a.util.debug("No Storage service available.")
}au=null
}p.firstMessage=e==0?true:false;
p.isOpen=false;
p.ctime=a.util.now();
if(p.uuid===0){p.uuid=e
}ao.closedByClientTimeout=false;
if(p.transport!=="websocket"&&p.transport!=="sse"){I(p)
}else{if(p.transport==="websocket"){if(!aq()){aw("Websocket is not supported, using request.fallbackTransport ("+p.fallbackTransport+")")
}else{ab(false)
}}else{if(p.transport==="sse"){if(!ap()){aw("Server Side Events(SSE) is not supported, using request.fallbackTransport ("+p.fallbackTransport+")")
}else{A(false)
}}}}}function aA(aH){var aI,aG,aL,aD="atmosphere-"+aH.url,aE={storage:function(){function aM(aQ){if(aQ.key===aD&&aQ.newValue){aF(aQ.newValue)
}}if(!a.util.storage){return
}var aP=window.localStorage,aN=function(aQ){return a.util.parseJSON(aP.getItem(aD+"-"+aQ))
},aO=function(aQ,aR){aP.setItem(aD+"-"+aQ,a.util.stringifyJSON(aR))
};
return{init:function(){aO("children",aN("children").concat([O]));
a.util.on(window,"storage",aM);
return aN("opened")
},signal:function(aQ,aR){aP.setItem(aD,a.util.stringifyJSON({target:"p",type:aQ,data:aR}))
},close:function(){var aQ=aN("children");
a.util.off(window,"storage",aM);
if(aQ){if(aJ(aQ,aH.id)){aO("children",aQ)
}}}}
},windowref:function(){var aM=window.open("",aD.replace(/\W/g,""));
if(!aM||aM.closed||!aM.callbacks){return
}return{init:function(){aM.callbacks.push(aF);
aM.children.push(O);
return aM.opened
},signal:function(aN,aO){if(!aM.closed&&aM.fire){aM.fire(a.util.stringifyJSON({target:"p",type:aN,data:aO}))
}},close:function(){if(!aL){aJ(aM.callbacks,aF);
aJ(aM.children,O)
}}}
}};
function aJ(aP,aO){var aM,aN=aP.length;
for(aM=0;
aM<aN;
aM++){if(aP[aM]===aO){aP.splice(aM,1)
}}return aN!==aP.length
}function aF(aM){var aO=a.util.parseJSON(aM),aN=aO.data;
if(aO.target==="c"){switch(aO.type){case"open":S("opening","local",p);
break;
case"close":if(!aL){aL=true;
if(aN.reason==="aborted"){F()
}else{if(aN.heir===O){W()
}else{setTimeout(function(){W()
},100)
}}}break;
case"message":l(aN,"messageReceived",200,aH.transport);
break;
case"localMessage":D(aN);
break
}}}function aK(){var aM=new RegExp("(?:^|; )("+encodeURIComponent(aD)+")=([^;]*)").exec(document.cookie);
if(aM){return a.util.parseJSON(decodeURIComponent(aM[2]))
}}aI=aK();
if(!aI||a.util.now()-aI.ts>1000){return
}aG=aE.storage()||aE.windowref();
if(!aG){return
}return{open:function(){var aM;
x=setInterval(function(){var aN=aI;
aI=aK();
if(!aI||aN.ts===aI.ts){aF(a.util.stringifyJSON({target:"c",type:"close",data:{reason:"error",heir:aN.heir}}))
}},1000);
aM=aG.init();
if(aM){setTimeout(function(){S("opening","local",aH)
},50)
}return aM
},send:function(aM){aG.signal("send",aM)
},localSend:function(aM){aG.signal("localSend",a.util.stringifyJSON({id:O,event:aM}))
},close:function(){if(!al){clearInterval(x);
aG.signal("close");
aG.close()
}}}
}function aB(){var aE,aD="atmosphere-"+p.url,aI={storage:function(){function aJ(aL){if(aL.key===aD&&aL.newValue){aF(aL.newValue)
}}if(!a.util.storage){return
}var aK=window.localStorage;
return{init:function(){a.util.on(window,"storage",aJ)
},signal:function(aL,aM){aK.setItem(aD,a.util.stringifyJSON({target:"c",type:aL,data:aM}))
},get:function(aL){return a.util.parseJSON(aK.getItem(aD+"-"+aL))
},set:function(aL,aM){aK.setItem(aD+"-"+aL,a.util.stringifyJSON(aM))
},close:function(){a.util.off(window,"storage",aJ);
aK.removeItem(aD);
aK.removeItem(aD+"-opened");
aK.removeItem(aD+"-children")
}}
},windowref:function(){var aK=aD.replace(/\W/g,""),aJ=document.getElementById(aK),aL;
if(!aJ){aJ=document.createElement("div");
aJ.id=aK;
aJ.style.display="none";
aJ.innerHTML='<iframe name="'+aK+'" />';
document.body.appendChild(aJ)
}aL=aJ.firstChild.contentWindow;
return{init:function(){aL.callbacks=[aF];
aL.fire=function(aM){var aN;
for(aN=0;
aN<aL.callbacks.length;
aN++){aL.callbacks[aN](aM)
}}
},signal:function(aM,aN){if(!aL.closed&&aL.fire){aL.fire(a.util.stringifyJSON({target:"c",type:aM,data:aN}))
}},get:function(aM){return !aL.closed?aL[aM]:null
},set:function(aM,aN){if(!aL.closed){aL[aM]=aN
}},close:function(){}}
}};
function aF(aJ){var aL=a.util.parseJSON(aJ),aK=aL.data;
if(aL.target==="p"){switch(aL.type){case"send":t(aK);
break;
case"localSend":D(aK);
break;
case"close":F();
break
}}}N=function aH(aJ){aE.signal("message",aJ)
};
function aG(){document.cookie=aC+"="+encodeURIComponent(a.util.stringifyJSON({ts:a.util.now()+1,heir:(aE.get("children")||[])[0]}))+"; path=/"
}aE=aI.storage()||aI.windowref();
aE.init();
if(p.logLevel==="debug"){a.util.debug("Installed StorageService "+aE)
}aE.set("children",[]);
if(aE.get("opened")!=null&&!aE.get("opened")){aE.set("opened",false)
}aC=encodeURIComponent(aD);
aG();
x=setInterval(aG,1000);
h=aE
}function S(aF,aI,aE){if(p.shared&&aI!=="local"){aB()
}if(h!=null){h.set("opened",true)
}aE.close=function(){F()
};
if(av>0&&aF==="re-connecting"){aE.isReopen=true;
q(ao)
}else{if(ao.error==null){ao.request=aE;
var aG=ao.state;
ao.state=aF;
var aD=ao.transport;
ao.transport=aI;
var aH=ao.responseBody;
ae();
ao.responseBody=aH;
ao.state=aG;
ao.transport=aD
}}}function ay(aF){aF.transport="jsonp";
var aE=p,aD;
if((aF!=null)&&(typeof(aF)!=="undefined")){aE=aF
}U={open:function(){var aH="atmosphere"+(++O);
function aG(){var aI=aE.url;
if(aE.dispatchUrl!=null){aI+=aE.dispatchUrl
}var aK=aE.data;
if(aE.attachHeadersAsQueryString){aI=o(aE);
if(aK!==""){aI+="&X-Atmosphere-Post-Body="+encodeURIComponent(aK)
}aK=""
}var aJ=document.head||document.getElementsByTagName("head")[0]||document.documentElement;
aD=document.createElement("script");
aD.src=aI+"&jsonpTransport="+aH;
aD.clean=function(){aD.clean=aD.onerror=aD.onload=aD.onreadystatechange=null;
if(aD.parentNode){aD.parentNode.removeChild(aD)
}};
aD.onload=aD.onreadystatechange=function(){if(!aD.readyState||/loaded|complete/.test(aD.readyState)){aD.clean()
}};
aD.onerror=function(){aD.clean();
aE.lastIndex=0;
if(aE.openId){clearTimeout(aE.openId)
}if(aE.heartbeatTimer){clearTimeout(aE.heartbeatTimer)
}if(aE.reconnect&&av++<aE.maxReconnectOnClose){S("re-connecting",aE.transport,aE);
aj(U,aE,aF.reconnectInterval);
aE.openId=setTimeout(function(){V(aE)
},aE.reconnectInterval+1000)
}else{Q(0,"maxReconnectOnClose reached")
}};
aJ.insertBefore(aD,aJ.firstChild)
}window[aH]=function(aK){if(aE.reconnect){if(aE.maxRequest===-1||aE.requestCount++<aE.maxRequest){j(aE);
if(!aE.executeCallbackBeforeReconnect){aj(U,aE,aE.pollingInterval)
}if(aK!=null&&typeof aK!=="string"){try{aK=aK.message
}catch(aJ){}}var aI=r(aK,aE,ao);
if(!aI){l(ao.responseBody,"messageReceived",200,aE.transport)
}if(aE.executeCallbackBeforeReconnect){aj(U,aE,aE.pollingInterval)
}}else{a.util.log(p.logLevel,["JSONP reconnect maximum try reached "+p.requestCount]);
Q(0,"maxRequest reached")
}}};
setTimeout(function(){aG()
},50)
},abort:function(){if(aD&&aD.clean){aD.clean()
}}};
U.open()
}function ar(aD){if(p.webSocketImpl!=null){return p.webSocketImpl
}else{if(window.WebSocket){return new WebSocket(aD)
}else{return new MozWebSocket(aD)
}}}function v(){return o(p,a.util.getAbsoluteURL(p.webSocketUrl||p.url)).replace(/^http/,"ws")
}function P(){var aD=o(p);
return aD
}function A(aE){ao.transport="sse";
var aD=P();
if(p.logLevel==="debug"){a.util.debug("Invoking executeSSE");
a.util.debug("Using URL: "+aD)
}if(aE&&!p.reconnect){if(ac!=null){k()
}return
}try{ac=new EventSource(aD,{withCredentials:p.withCredentials})
}catch(aF){Q(0,aF);
aw("SSE failed. Downgrading to fallback transport and resending");
return
}if(p.connectTimeout>0){p.id=setTimeout(function(){if(!aE){k()
}},p.connectTimeout)
}ac.onopen=function(aG){j(p);
if(p.logLevel==="debug"){a.util.debug("SSE successfully opened")
}if(!p.enableProtocol){if(!aE){S("opening","sse",p)
}else{S("re-opening","sse",p)
}}else{if(p.isReopen){p.isReopen=false;
S("re-opening",p.transport,p)
}}aE=true;
if(p.method==="POST"){ao.state="messageReceived";
ac.send(p.data)
}};
ac.onmessage=function(aH){j(p);
if(!p.enableXDR&&aH.origin&&aH.origin!==window.location.protocol+"//"+window.location.host){a.util.log(p.logLevel,["Origin was not "+window.location.protocol+"//"+window.location.host]);
return
}ao.state="messageReceived";
ao.status=200;
aH=aH.data;
var aG=r(aH,p,ao);
if(!aG){ae();
ao.responseBody="";
ao.messages=[]
}};
ac.onerror=function(aG){clearTimeout(p.id);
if(p.heartbeatTimer){clearTimeout(p.heartbeatTimer)
}if(ao.closedByClientTimeout){return
}aa(aE);
k();
if(al){a.util.log(p.logLevel,["SSE closed normally"])
}else{if(!aE){aw("SSE failed. Downgrading to fallback transport and resending")
}else{if(p.reconnect&&(ao.transport==="sse")){if(av++<p.maxReconnectOnClose){S("re-connecting",p.transport,p);
if(p.reconnectInterval>0){p.reconnectId=setTimeout(function(){A(true)
},p.reconnectInterval)
}else{A(true)
}ao.responseBody="";
ao.messages=[]
}else{a.util.log(p.logLevel,["SSE reconnect maximum try reached "+av]);
Q(0,"maxReconnectOnClose reached")
}}}}}
}function ab(aE){ao.transport="websocket";
var aD=v(p.url);
if(p.logLevel==="debug"){a.util.debug("Invoking executeWebSocket");
a.util.debug("Using URL: "+aD)
}if(aE&&!p.reconnect){if(at!=null){k()
}return
}at=ar(aD);
if(p.webSocketBinaryType!=null){at.binaryType=p.webSocketBinaryType
}if(p.connectTimeout>0){p.id=setTimeout(function(){if(!aE){var aH={code:1002,reason:"",wasClean:false};
at.onclose(aH);
try{k()
}catch(aI){}return
}},p.connectTimeout)
}at.onopen=function(aI){j(p);
if(p.logLevel==="debug"){a.util.debug("Websocket successfully opened")
}var aH=aE;
if(at!=null){at.canSendMessage=true
}if(!p.enableProtocol){aE=true;
if(aH){S("re-opening","websocket",p)
}else{S("opening","websocket",p)
}}if(at!=null){if(p.method==="POST"){ao.state="messageReceived";
at.send(p.data)
}}};
at.onmessage=function(aJ){j(p);
if(p.enableProtocol){aE=true
}ao.state="messageReceived";
ao.status=200;
aJ=aJ.data;
var aH=typeof(aJ)==="string";
if(aH){var aI=r(aJ,p,ao);
if(!aI){ae();
ao.responseBody="";
ao.messages=[]
}}else{aJ=s(p,aJ);
if(aJ===""){return
}ao.responseBody=aJ;
ae();
ao.responseBody=null
}};
at.onerror=function(aH){clearTimeout(p.id);
if(p.heartbeatTimer){clearTimeout(p.heartbeatTimer)
}};
at.onclose=function(aH){clearTimeout(p.id);
if(ao.state==="closed"){return
}var aI=aH.reason;
if(aI===""){switch(aH.code){case 1000:aI="Normal closure; the connection successfully completed whatever purpose for which it was created.";
break;
case 1001:aI="The endpoint is going away, either because of a server failure or because the browser is navigating away from the page that opened the connection.";
break;
case 1002:aI="The endpoint is terminating the connection due to a protocol error.";
break;
case 1003:aI="The connection is being terminated because the endpoint received data of a type it cannot accept (for example, a text-only endpoint received binary data).";
break;
case 1004:aI="The endpoint is terminating the connection because a data frame was received that is too large.";
break;
case 1005:aI="Unknown: no status code was provided even though one was expected.";
break;
case 1006:aI="Connection was closed abnormally (that is, with no close frame being sent).";
break
}}if(p.logLevel==="warn"){a.util.warn("Websocket closed, reason: "+aI);
a.util.warn("Websocket closed, wasClean: "+aH.wasClean)
}if(ao.closedByClientTimeout){return
}aa(aE);
ao.state="closed";
if(al){a.util.log(p.logLevel,["Websocket closed normally"])
}else{if(!aE){aw("Websocket failed. Downgrading to Comet and resending")
}else{if(p.reconnect&&ao.transport==="websocket"&&aH.code!==1001){k();
if(av++<p.maxReconnectOnClose){S("re-connecting",p.transport,p);
if(p.reconnectInterval>0){p.reconnectId=setTimeout(function(){ao.responseBody="";
ao.messages=[];
ab(true)
},p.reconnectInterval)
}else{ao.responseBody="";
ao.messages=[];
ab(true)
}}else{a.util.log(p.logLevel,["Websocket reconnect maximum try reached "+p.requestCount]);
if(p.logLevel==="warn"){a.util.warn("Websocket error, reason: "+aH.reason)
}Q(0,"maxReconnectOnClose reached")
}}}}};
var aF=navigator.userAgent.toLowerCase();
var aG=aF.indexOf("android")>-1;
if(aG&&at.url===undefined){at.onclose({reason:"Android 4.1 does not support websockets.",wasClean:false})
}}function s(aJ,aI){var aH=aI;
if(aJ.transport==="polling"){return aH
}if(a.util.trim(aI).length!==0&&aJ.enableProtocol&&aJ.firstMessage){var aK=aJ.trackMessageLength?1:0;
var aG=aI.split(aJ.messageDelimiter);
if(aG.length<=aK+1){return aH
}aJ.firstMessage=false;
aJ.uuid=a.util.trim(aG[aK]);
if(aG.length<=aK+2){a.util.log("error",["Protocol data not sent by the server. If you enable protocol on client side, be sure to install JavascriptProtocol interceptor on server side.Also note that atmosphere-runtime 2.2+ should be used."])
}var aD=parseInt(a.util.trim(aG[aK+1]),10);
ag=aG[aK+2];
if(!isNaN(aD)&&aD>0){var aF=function(){t(ag);
aJ.heartbeatTimer=setTimeout(aF,aD)
};
aJ.heartbeatTimer=setTimeout(aF,aD)
}if(aJ.transport!=="long-polling"){V(aJ)
}e=aJ.uuid;
aH="";
aK=aJ.trackMessageLength?4:3;
if(aG.length>aK+1){for(var aE=aK;
aE<aG.length;
aE++){aH+=aG[aE];
if(aE+1!==aG.length){aH+=aJ.messageDelimiter
}}}if(aJ.ackInterval!==0){setTimeout(function(){t("...ACK...")
},aJ.ackInterval)
}}else{if(aJ.enableProtocol&&aJ.firstMessage&&a.util.browser.msie&&+a.util.browser.version.split(".")[0]<10){a.util.log(p.logLevel,["Receiving unexpected data from IE"])
}else{V(aJ)
}}return aH
}function j(aD){aD.timedOut=false;
clearTimeout(aD.id);
if(aD.timeout>0&&aD.transport!=="polling"){aD.id=setTimeout(function(){aD.timedOut=true;
az(aD);
B();
k()
},aD.timeout)
}}function az(aD){ao.closedByClientTimeout=true;
ao.state="closedByClient";
ao.responseBody="";
ao.status=408;
ao.messages=[];
ae()
}function Q(aD,aE){k();
clearTimeout(p.id);
ao.state="error";
ao.reasonPhrase=aE;
ao.responseBody="";
ao.status=aD;
ao.messages=[];
ae()
}function r(aH,aG,aD){aH=s(aG,aH);
if(aH.length===0){return true
}aD.responseBody=aH;
if(aG.trackMessageLength){aH=aD.partialMessage+aH;
var aF=[];
var aE=aH.indexOf(aG.messageDelimiter);
while(aE!==-1){var aJ=aH.substring(0,aE);
var aI=+aJ;
if(isNaN(aI)){throw new Error('message length "'+aJ+'" is not a number')
}aE+=aG.messageDelimiter.length;
if(aE+aI>aH.length){aE=-1
}else{aF.push(aH.substring(aE,aE+aI));
aH=aH.substring(aE+aI,aH.length);
aE=aH.indexOf(aG.messageDelimiter)
}}aD.partialMessage=aH;
if(aF.length!==0){aD.responseBody=aF.join(aG.messageDelimiter);
aD.messages=aF;
return false
}else{aD.responseBody="";
aD.messages=[];
return true
}}else{aD.responseBody=aH
}return false
}function aw(aD){a.util.log(p.logLevel,[aD]);
if(typeof(p.onTransportFailure)!=="undefined"){p.onTransportFailure(aD,p)
}else{if(typeof(a.util.onTransportFailure)!=="undefined"){a.util.onTransportFailure(aD,p)
}}p.transport=p.fallbackTransport;
var aE=p.connectTimeout===-1?0:p.connectTimeout;
if(p.reconnect&&p.transport!=="none"||p.transport==null){p.method=p.fallbackMethod;
ao.transport=p.fallbackTransport;
p.fallbackTransport="none";
if(aE>0){p.reconnectId=setTimeout(function(){W()
},aE)
}else{W()
}}else{Q(500,"Unable to reconnect with fallback transport")
}}function o(aF,aD){var aE=p;
if((aF!=null)&&(typeof(aF)!=="undefined")){aE=aF
}if(aD==null){aD=aE.url
}if(!aE.attachHeadersAsQueryString){return aD
}if(aD.indexOf("X-Atmosphere-Framework")!==-1){return aD
}aD+=(aD.indexOf("?")!==-1)?"&":"?";
aD+="X-Atmosphere-tracking-id="+aE.uuid;
aD+="&X-Atmosphere-Framework="+c;
aD+="&X-Atmosphere-Transport="+aE.transport;
if(aE.trackMessageLength){aD+="&X-Atmosphere-TrackMessageSize=true"
}if(aE.heartbeat!==null&&aE.heartbeat.server!==null){aD+="&X-Heartbeat-Server="+aE.heartbeat.server
}if(aE.contentType!==""){aD+="&Content-Type="+(aE.transport==="websocket"?aE.contentType:encodeURIComponent(aE.contentType))
}if(aE.enableProtocol){aD+="&X-atmo-protocol=true"
}a.util.each(aE.headers,function(aG,aI){var aH=a.util.isFunction(aI)?aI.call(this,aE,aF,ao):aI;
if(aH!=null){aD+="&"+encodeURIComponent(aG)+"="+encodeURIComponent(aH)
}});
return aD
}function V(aD){if(!aD.isOpen){aD.isOpen=true;
S("opening",aD.transport,aD)
}else{if(aD.isReopen){aD.isReopen=false;
S("re-opening",aD.transport,aD)
}else{if(ao.state==="messageReceived"&&(aD.transport==="jsonp"||aD.transport==="long-polling")){am(ao)
}}}}function I(aG){var aE=p;
if((aG!=null)||(typeof(aG)!=="undefined")){aE=aG
}aE.lastIndex=0;
aE.readyState=0;
if((aE.transport==="jsonp")||((aE.enableXDR)&&(a.util.checkCORSSupport()))){ay(aE);
return
}if(a.util.browser.msie&&+a.util.browser.version.split(".")[0]<10){if((aE.transport==="streaming")){if(aE.enableXDR&&window.XDomainRequest){M(aE)
}else{ax(aE)
}return
}if((aE.enableXDR)&&(window.XDomainRequest)){M(aE);
return
}}var aH=function(){aE.lastIndex=0;
if(aE.reconnect&&av++<aE.maxReconnectOnClose){ao.ffTryingReconnect=true;
S("re-connecting",aG.transport,aG);
aj(aF,aE,aG.reconnectInterval)
}else{Q(0,"maxReconnectOnClose reached")
}};
var aD=function(){ao.errorHandled=true;
k();
aH()
};
if(aE.force||(aE.reconnect&&(aE.maxRequest===-1||aE.requestCount++<aE.maxRequest))){aE.force=false;
var aF=a.util.xhr();
aF.hasData=false;
J(aF,aE,true);
if(aE.suspend){y=aF
}if(aE.transport!=="polling"){ao.transport=aE.transport;
aF.onabort=function(){aa(true)
};
aF.onerror=function(){ao.error=true;
ao.ffTryingReconnect=true;
try{ao.status=XMLHttpRequest.status
}catch(aJ){ao.status=500
}if(!ao.status){ao.status=500
}if(!ao.errorHandled){k();
aH()
}}
}aF.onreadystatechange=function(){if(al){return
}ao.error=null;
var aK=false;
var aQ=false;
if(aE.transport==="streaming"&&aE.readyState>2&&aF.readyState===4){k();
aH();
return
}aE.readyState=aF.readyState;
if(aE.transport==="streaming"&&aF.readyState>=3){aQ=true
}else{if(aE.transport==="long-polling"&&aF.readyState===4){aQ=true
}}j(p);
if(aE.transport!=="polling"){var aJ=200;
if(aF.readyState===4){aJ=aF.status>1000?0:aF.status
}if(!aE.reconnectOnServerError&&(aJ>=300&&aJ<600)){Q(aJ,aF.statusText);
return
}if(aJ>=300||aJ===0){aD();
return
}if((!aE.enableProtocol||!aG.firstMessage)&&aF.readyState===2){if(a.util.browser.mozilla&&ao.ffTryingReconnect){ao.ffTryingReconnect=false;
setTimeout(function(){if(!ao.ffTryingReconnect){V(aE)
}},500)
}else{V(aE)
}}}else{if(aF.readyState===4){aQ=true
}}if(aQ){var aN=aF.responseText;
ao.errorHandled=false;
if(a.util.trim(aN).length===0&&aE.transport==="long-polling"){if(!aF.hasData){aj(aF,aE,aE.pollingInterval)
}else{aF.hasData=false
}return
}aF.hasData=true;
E(aF,p);
if(aE.transport==="streaming"){if(!a.util.browser.opera){var aM=aN.substring(aE.lastIndex,aN.length);
aK=r(aM,aE,ao);
aE.lastIndex=aN.length;
if(aK){return
}}else{a.util.iterate(function(){if(ao.status!==500&&aF.responseText.length>aE.lastIndex){try{ao.status=aF.status;
ao.headers=a.util.parseHeaders(aF.getAllResponseHeaders());
E(aF,p)
}catch(aS){ao.status=404
}j(p);
ao.state="messageReceived";
var aR=aF.responseText.substring(aE.lastIndex);
aE.lastIndex=aF.responseText.length;
aK=r(aR,aE,ao);
if(!aK){ae()
}if(G(aF,aE)){H(aF,aE);
return
}}else{if(ao.status>400){aE.lastIndex=aF.responseText.length;
return false
}}},0)
}}else{aK=r(aN,aE,ao)
}var aP=G(aF,aE);
try{ao.status=aF.status;
ao.headers=a.util.parseHeaders(aF.getAllResponseHeaders());
E(aF,aE)
}catch(aO){ao.status=404
}if(aE.suspend){ao.state=ao.status===0?"closed":"messageReceived"
}else{ao.state="messagePublished"
}var aL=!aP&&aG.transport!=="streaming"&&aG.transport!=="polling";
if(aL&&!aE.executeCallbackBeforeReconnect){aj(aF,aE,aE.pollingInterval)
}if(ao.responseBody.length!==0&&!aK){ae()
}if(aL&&aE.executeCallbackBeforeReconnect){aj(aF,aE,aE.pollingInterval)
}if(aP){H(aF,aE)
}}};
try{aF.send(aE.data);
u=true
}catch(aI){a.util.log(aE.logLevel,["Unable to connect to "+aE.url]);
Q(0,aI)
}}else{if(aE.logLevel==="debug"){a.util.log(aE.logLevel,["Max re-connection reached."])
}Q(0,"maxRequest reached")
}}function H(aE,aD){F();
al=false;
aj(aE,aD,500)
}function J(aF,aG,aE){var aD=aG.url;
if(aG.dispatchUrl!=null&&aG.method==="POST"){aD+=aG.dispatchUrl
}aD=o(aG,aD);
aD=a.util.prepareURL(aD);
if(aE){aF.open(aG.method,aD,aG.async);
if(aG.connectTimeout>0){aG.id=setTimeout(function(){if(aG.requestCount===0){k();
l("Connect timeout","closed",200,aG.transport)
}},aG.connectTimeout)
}}if(p.withCredentials&&p.transport!=="websocket"){if("withCredentials" in aF){aF.withCredentials=true
}}if(!p.dropHeaders){aF.setRequestHeader("X-Atmosphere-Framework",a.util.version);
aF.setRequestHeader("X-Atmosphere-Transport",aG.transport);
if(aF.heartbeat!==null&&aF.heartbeat.server!==null){aF.setRequestHeader("X-Heartbeat-Server",aF.heartbeat.server)
}if(aG.trackMessageLength){aF.setRequestHeader("X-Atmosphere-TrackMessageSize","true")
}aF.setRequestHeader("X-Atmosphere-tracking-id",aG.uuid);
a.util.each(aG.headers,function(aH,aJ){var aI=a.util.isFunction(aJ)?aJ.call(this,aF,aG,aE,ao):aJ;
if(aI!=null){aF.setRequestHeader(aH,aI)
}})
}if(aG.contentType!==""){aF.setRequestHeader("Content-Type",aG.contentType)
}}function aj(aE,aF,aG){if(aF.reconnect||(aF.suspend&&u)){var aD=0;
if(aE&&aE.readyState>1){aD=aE.status>1000?0:aE.status
}ao.status=aD===0?204:aD;
ao.reason=aD===0?"Server resumed the connection or down.":"OK";
clearTimeout(aF.id);
if(aF.reconnectId){clearTimeout(aF.reconnectId);
delete aF.reconnectId
}if(aG>0){p.reconnectId=setTimeout(function(){I(aF)
},aG)
}else{I(aF)
}}}function q(aD){aD.state="re-connecting";
ah(aD)
}function am(aD){aD.state="openAfterResume";
ah(aD);
aD.state="messageReceived"
}function M(aD){if(aD.transport!=="polling"){n=Y(aD);
n.open()
}else{Y(aD).open()
}}function Y(aF){var aE=p;
if((aF!=null)&&(typeof(aF)!=="undefined")){aE=aF
}var aK=aE.transport;
var aJ=0;
var aD=new window.XDomainRequest();
var aH=function(){if(aE.transport==="long-polling"&&(aE.reconnect&&(aE.maxRequest===-1||aE.requestCount++<aE.maxRequest))){aD.status=200;
M(aE)
}};
var aI=aE.rewriteURL||function(aM){var aL=/(?:^|;\s*)(JSESSIONID|PHPSESSID)=([^;]*)/.exec(document.cookie);
switch(aL&&aL[1]){case"JSESSIONID":return aM.replace(/;jsessionid=[^\?]*|(\?)|$/,";jsessionid="+aL[2]+"$1");
case"PHPSESSID":return aM.replace(/\?PHPSESSID=[^&]*&?|\?|$/,"?PHPSESSID="+aL[2]+"&").replace(/&$/,"")
}return aM
};
aD.onprogress=function(){aG(aD)
};
aD.onerror=function(){if(aE.transport!=="polling"){k();
if(av++<aE.maxReconnectOnClose){if(aE.reconnectInterval>0){aE.reconnectId=setTimeout(function(){S("re-connecting",aF.transport,aF);
M(aE)
},aE.reconnectInterval)
}else{S("re-connecting",aF.transport,aF);
M(aE)
}}else{Q(0,"maxReconnectOnClose reached")
}}};
aD.onload=function(){if(p.timedOut){p.timedOut=false;
k();
aE.lastIndex=0;
if(aE.reconnect&&av++<aE.maxReconnectOnClose){S("re-connecting",aF.transport,aF);
aH()
}else{Q(0,"maxReconnectOnClose reached")
}}};
var aG=function(aL){clearTimeout(aE.id);
var aN=aL.responseText;
aN=aN.substring(aJ);
aJ+=aN.length;
if(aK!=="polling"){j(aE);
var aM=r(aN,aE,ao);
if(aK==="long-polling"&&a.util.trim(aN).length===0){return
}if(aE.executeCallbackBeforeReconnect){aH()
}if(!aM){l(ao.responseBody,"messageReceived",200,aK)
}if(!aE.executeCallbackBeforeReconnect){aH()
}}};
return{open:function(){var aL=aE.url;
if(aE.dispatchUrl!=null){aL+=aE.dispatchUrl
}aL=o(aE,aL);
aD.open(aE.method,aI(aL));
if(aE.method==="GET"){aD.send()
}else{aD.send(aE.data)
}if(aE.connectTimeout>0){aE.id=setTimeout(function(){if(aE.requestCount===0){k();
l("Connect timeout","closed",200,aE.transport)
}},aE.connectTimeout)
}},close:function(){aD.abort()
}}
}function ax(aD){n=X(aD);
n.open()
}function X(aG){var aF=p;
if((aG!=null)&&(typeof(aG)!=="undefined")){aF=aG
}var aE;
var aH=new window.ActiveXObject("htmlfile");
aH.open();
aH.close();
var aD=aF.url;
if(aF.dispatchUrl!=null){aD+=aF.dispatchUrl
}if(aF.transport!=="polling"){ao.transport=aF.transport
}return{open:function(){var aI=aH.createElement("iframe");
aD=o(aF);
if(aF.data!==""){aD+="&X-Atmosphere-Post-Body="+encodeURIComponent(aF.data)
}aD=a.util.prepareURL(aD);
aI.src=aD;
aH.body.appendChild(aI);
var aJ=aI.contentDocument||aI.contentWindow.document;
aE=a.util.iterate(function(){try{if(!aJ.firstChild){return
}var aM=aJ.body?aJ.body.lastChild:aJ;
var aO=function(){var aQ=aM.cloneNode(true);
aQ.appendChild(aJ.createTextNode("."));
var aP=aQ.innerText;
aP=aP.substring(0,aP.length-1);
return aP
};
if(!aJ.body||!aJ.body.firstChild||aJ.body.firstChild.nodeName.toLowerCase()!=="pre"){var aL=aJ.head||aJ.getElementsByTagName("head")[0]||aJ.documentElement||aJ;
var aK=aJ.createElement("script");
aK.text="document.write('<plaintext>')";
aL.insertBefore(aK,aL.firstChild);
aL.removeChild(aK);
aM=aJ.body.lastChild
}if(aF.closed){aF.isReopen=true
}aE=a.util.iterate(function(){var aQ=aO();
if(aQ.length>aF.lastIndex){j(p);
ao.status=200;
ao.error=null;
aM.innerText="";
var aP=r(aQ,aF,ao);
if(aP){return""
}l(ao.responseBody,"messageReceived",200,aF.transport)
}aF.lastIndex=0;
if(aJ.readyState==="complete"){aa(true);
S("re-connecting",aF.transport,aF);
if(aF.reconnectInterval>0){aF.reconnectId=setTimeout(function(){ax(aF)
},aF.reconnectInterval)
}else{ax(aF)
}return false
}},null);
return false
}catch(aN){ao.error=true;
S("re-connecting",aF.transport,aF);
if(av++<aF.maxReconnectOnClose){if(aF.reconnectInterval>0){aF.reconnectId=setTimeout(function(){ax(aF)
},aF.reconnectInterval)
}else{ax(aF)
}}else{Q(0,"maxReconnectOnClose reached")
}aH.execCommand("Stop");
aH.close();
return false
}})
},close:function(){if(aE){aE()
}aH.execCommand("Stop");
aa(true)
}}
}function t(aD){if(au!=null){C(aD)
}else{if(y!=null||ac!=null){L(aD)
}else{if(n!=null){i(aD)
}else{if(U!=null){z(aD)
}else{if(at!=null){T(aD)
}else{Q(0,"No suspended connection available");
a.util.error("No suspended connection available. Make sure atmosphere.subscribe has been called and request.onOpen invoked before invoking this method")
}}}}}}function ai(aE,aD){if(!aD){aD=w(aE)
}aD.transport="polling";
aD.method="GET";
aD.withCredentials=false;
aD.reconnect=false;
aD.force=true;
aD.suspend=false;
aD.timeout=1000;
I(aD)
}function C(aD){au.send(aD)
}function an(aE){if(aE.length===0){return
}try{if(au){au.localSend(aE)
}else{if(h){h.signal("localMessage",a.util.stringifyJSON({id:O,event:aE}))
}}}catch(aD){a.util.error(aD)
}}function L(aE){var aD=w(aE);
I(aD)
}function i(aE){if(p.enableXDR&&a.util.checkCORSSupport()){var aD=w(aE);
aD.reconnect=false;
ay(aD)
}else{L(aE)
}}function z(aD){L(aD)
}function K(aD){var aE=aD;
if(typeof(aE)==="object"){aE=aD.data
}return aE
}function w(aE){var aF=K(aE);
var aD={connected:false,timeout:60000,method:"POST",url:p.url,contentType:p.contentType,headers:p.headers,reconnect:true,callback:null,data:aF,suspend:false,maxRequest:-1,logLevel:"info",requestCount:0,withCredentials:p.withCredentials,async:p.async,transport:"polling",isOpen:true,attachHeadersAsQueryString:true,enableXDR:p.enableXDR,uuid:p.uuid,dispatchUrl:p.dispatchUrl,enableProtocol:false,messageDelimiter:"|",trackMessageLength:p.trackMessageLength,maxReconnectOnClose:p.maxReconnectOnClose,heartbeatTimer:p.heartbeatTimer,heartbeat:p.heartbeat};
if(typeof(aE)==="object"){aD=a.util.extend(aD,aE)
}return aD
}function T(aD){var aG=a.util.isBinary(aD)?aD:K(aD);
var aE;
try{if(p.dispatchUrl!=null){aE=p.webSocketPathDelimiter+p.dispatchUrl+p.webSocketPathDelimiter+aG
}else{aE=aG
}if(!at.canSendMessage){a.util.error("WebSocket not connected.");
return
}at.send(aE)
}catch(aF){at.onclose=function(aH){};
k();
aw("Websocket failed. Downgrading to Comet and resending "+aD);
L(aD)
}}function D(aE){var aD=a.util.parseJSON(aE);
if(aD.id!==O){if(typeof(p.onLocalMessage)!=="undefined"){p.onLocalMessage(aD.event)
}else{if(typeof(a.util.onLocalMessage)!=="undefined"){a.util.onLocalMessage(aD.event)
}}}}function l(aG,aD,aE,aF){ao.responseBody=aG;
ao.transport=aF;
ao.status=aE;
ao.state=aD;
ae()
}function E(aD,aF){if(!aF.readResponsesHeaders){if(!aF.enableProtocol){aF.uuid=O
}}else{try{var aE=aD.getResponseHeader("X-Atmosphere-tracking-id");
if(aE&&aE!=null){aF.uuid=aE.split(" ").pop()
}}catch(aG){}}}function ah(aD){m(aD,p);
m(aD,a.util)
}function m(aE,aF){switch(aE.state){case"messageReceived":av=0;
if(typeof(aF.onMessage)!=="undefined"){aF.onMessage(aE)
}if(typeof(aF.onmessage)!=="undefined"){aF.onmessage(aE)
}break;
case"error":if(typeof(aF.onError)!=="undefined"){aF.onError(aE)
}if(typeof(aF.onerror)!=="undefined"){aF.onerror(aE)
}break;
case"opening":delete p.closed;
if(typeof(aF.onOpen)!=="undefined"){aF.onOpen(aE)
}if(typeof(aF.onopen)!=="undefined"){aF.onopen(aE)
}break;
case"messagePublished":if(typeof(aF.onMessagePublished)!=="undefined"){aF.onMessagePublished(aE)
}break;
case"re-connecting":if(typeof(aF.onReconnect)!=="undefined"){aF.onReconnect(p,aE)
}break;
case"closedByClient":if(typeof(aF.onClientTimeout)!=="undefined"){aF.onClientTimeout(p)
}break;
case"re-opening":delete p.closed;
if(typeof(aF.onReopen)!=="undefined"){aF.onReopen(p,aE)
}break;
case"fail-to-reconnect":if(typeof(aF.onFailureToReconnect)!=="undefined"){aF.onFailureToReconnect(p,aE)
}break;
case"unsubscribe":case"closed":var aD=typeof(p.closed)!=="undefined"?p.closed:false;
if(!aD){if(typeof(aF.onClose)!=="undefined"){aF.onClose(aE)
}if(typeof(aF.onclose)!=="undefined"){aF.onclose(aE)
}}p.closed=true;
break;
case"openAfterResume":if(typeof(aF.onOpenAfterResume)!=="undefined"){aF.onOpenAfterResume(p)
}break
}}function aa(aD){if(ao.state!=="closed"){ao.state="closed";
ao.responseBody="";
ao.messages=[];
ao.status=!aD?501:200;
ae()
}}function ae(){var aF=function(aI,aJ){aJ(ao)
};
if(au==null&&N!=null){N(ao.responseBody)
}p.reconnect=p.mrequest;
var aD=typeof(ao.responseBody)==="string";
var aG=(aD&&p.trackMessageLength)?(ao.messages.length>0?ao.messages:[""]):new Array(ao.responseBody);
for(var aE=0;
aE<aG.length;
aE++){if(aG.length>1&&aG[aE].length===0){continue
}ao.responseBody=(aD)?a.util.trim(aG[aE]):aG[aE];
if(au==null&&N!=null){N(ao.responseBody)
}if((ao.responseBody.length===0||(aD&&ag===ao.responseBody))&&ao.state==="messageReceived"){continue
}ah(ao);
if(f.length>0){if(p.logLevel==="debug"){a.util.debug("Invoking "+f.length+" global callbacks: "+ao.state)
}try{a.util.each(f,aF)
}catch(aH){a.util.log(p.logLevel,["Callback exception"+aH])
}}if(typeof(p.callback)==="function"){if(p.logLevel==="debug"){a.util.debug("Invoking request callbacks")
}try{p.callback(ao)
}catch(aH){a.util.log(p.logLevel,["Callback exception"+aH])
}}}}this.subscribe=function(aD){ak(aD);
W()
};
this.execute=function(){W()
};
this.close=function(){F()
};
this.disconnect=function(){B()
};
this.getUrl=function(){return p.url
};
this.push=function(aF,aE){if(aE!=null){var aD=p.dispatchUrl;
p.dispatchUrl=aE;
t(aF);
p.dispatchUrl=aD
}else{t(aF)
}};
this.getUUID=function(){return p.uuid
};
this.pushLocal=function(aD){an(aD)
};
this.enableProtocol=function(aD){return p.enableProtocol
};
this.request=p;
this.response=ao
}};
a.subscribe=function(h,k,j){if(typeof(k)==="function"){a.addCallback(k)
}if(typeof(h)!=="string"){j=h
}else{j.url=h
}e=((typeof(j)!=="undefined")&&typeof(j.uuid)!=="undefined")?j.uuid:0;
var i=new a.AtmosphereRequest(j);
i.execute();
g[g.length]=i;
return i
};
a.unsubscribe=function(){if(g.length>0){var h=[].concat(g);
for(var k=0;
k<h.length;
k++){var j=h[k];
j.close();
clearTimeout(j.response.request.id);
if(j.heartbeatTimer){clearTimeout(j.heartbeatTimer)
}}}g=[];
f=[]
};
a.unsubscribeUrl=function(j){var h=-1;
if(g.length>0){for(var l=0;
l<g.length;
l++){var k=g[l];
if(k.getUrl()===j){k.close();
clearTimeout(k.response.request.id);
if(k.heartbeatTimer){clearTimeout(k.heartbeatTimer)
}h=l;
break
}}}if(h>=0){g.splice(h,1)
}};
a.addCallback=function(h){if(a.util.inArray(h,f)===-1){f.push(h)
}};
a.removeCallback=function(i){var h=a.util.inArray(i,f);
if(h!==-1){f.splice(h,1)
}};
a.util={browser:{},parseHeaders:function(i){var h,k=/^(.*?):[ \t]*([^\r\n]*)\r?$/mg,j={};
while(h=k.exec(i)){j[h[1]]=h[2]
}return j
},now:function(){return new Date().getTime()
},isArray:function(h){return Object.prototype.toString.call(h)==="[object Array]"
},inArray:function(k,l){if(!Array.prototype.indexOf){var h=l.length;
for(var j=0;
j<h;
++j){if(l[j]===k){return j
}}return -1
}return l.indexOf(k)
},isBinary:function(h){return/^\[object\s(?:Blob|ArrayBuffer|.+Array)\]$/.test(Object.prototype.toString.call(h))
},isFunction:function(h){return Object.prototype.toString.call(h)==="[object Function]"
},getAbsoluteURL:function(h){var i=document.createElement("div");
i.innerHTML='<a href="'+h+'"/>';
return encodeURI(decodeURI(i.firstChild.href))
},prepareURL:function(i){var j=a.util.now();
var h=i.replace(/([?&])_=[^&]*/,"$1_="+j);
return h+(h===i?(/\?/.test(i)?"&":"?")+"_="+j:"")
},trim:function(h){if(!String.prototype.trim){return h.toString().replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"").replace(/\s+/g," ")
}else{return h.toString().trim()
}},param:function(l){var j,h=[];
function k(m,n){n=a.util.isFunction(n)?n():(n==null?"":n);
h.push(encodeURIComponent(m)+"="+encodeURIComponent(n))
}function i(n,o){var m;
if(a.util.isArray(o)){a.util.each(o,function(q,p){if(/\[\]$/.test(n)){k(n,p)
}else{i(n+"["+(typeof p==="object"?q:"")+"]",p)
}})
}else{if(Object.prototype.toString.call(o)==="[object Object]"){for(m in o){i(n+"["+m+"]",o[m])
}}else{k(n,o)
}}}for(j in l){i(j,l[j])
}return h.join("&").replace(/%20/g,"+")
},storage:function(){try{return !!(window.localStorage&&window.StorageEvent)
}catch(h){return false
}},iterate:function(j,i){var k;
i=i||0;
(function h(){k=setTimeout(function(){if(j()===false){return
}h()
},i)
})();
return function(){clearTimeout(k)
}
},each:function(n,o,j){if(!n){return
}var m,k=0,l=n.length,h=a.util.isArray(n);
if(j){if(h){for(;
k<l;
k++){m=o.apply(n[k],j);
if(m===false){break
}}}else{for(k in n){m=o.apply(n[k],j);
if(m===false){break
}}}}else{if(h){for(;
k<l;
k++){m=o.call(n[k],k,n[k]);
if(m===false){break
}}}else{for(k in n){m=o.call(n[k],k,n[k]);
if(m===false){break
}}}}return n
},extend:function(l){var k,j,h;
for(k=1;
k<arguments.length;
k++){if((j=arguments[k])!=null){for(h in j){l[h]=j[h]
}}}return l
},on:function(j,i,h){if(j.addEventListener){j.addEventListener(i,h,false)
}else{if(j.attachEvent){j.attachEvent("on"+i,h)
}}},off:function(j,i,h){if(j.removeEventListener){j.removeEventListener(i,h,false)
}else{if(j.detachEvent){j.detachEvent("on"+i,h)
}}},log:function(j,i){if(window.console){var h=window.console[j];
if(typeof h==="function"){h.apply(window.console,i)
}}},warn:function(){a.util.log("warn",arguments)
},info:function(){a.util.log("info",arguments)
},debug:function(){a.util.log("debug",arguments)
},error:function(){a.util.log("error",arguments)
},xhr:function(){try{return new window.XMLHttpRequest()
}catch(i){try{return new window.ActiveXObject("Microsoft.XMLHTTP")
}catch(h){}}},parseJSON:function(h){return !h?null:window.JSON&&window.JSON.parse?window.JSON.parse(h):new Function("return "+h)()
},stringifyJSON:function(j){var m=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,k={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};
function h(n){return'"'+n.replace(m,function(o){var p=k[o];
return typeof p==="string"?p:"\\u"+("0000"+o.charCodeAt(0).toString(16)).slice(-4)
})+'"'
}function i(o){return o<10?"0"+o:o
}return window.JSON&&window.JSON.stringify?window.JSON.stringify(j):(function l(s,r){var q,p,n,o,u=r[s],t=typeof u;
if(u&&typeof u==="object"&&typeof u.toJSON==="function"){u=u.toJSON(s);
t=typeof u
}switch(t){case"string":return h(u);
case"number":return isFinite(u)?String(u):"null";
case"boolean":return String(u);
case"object":if(!u){return"null"
}switch(Object.prototype.toString.call(u)){case"[object Date]":return isFinite(u.valueOf())?'"'+u.getUTCFullYear()+"-"+i(u.getUTCMonth()+1)+"-"+i(u.getUTCDate())+"T"+i(u.getUTCHours())+":"+i(u.getUTCMinutes())+":"+i(u.getUTCSeconds())+'Z"':"null";
case"[object Array]":n=u.length;
o=[];
for(q=0;
q<n;
q++){o.push(l(q,u)||"null")
}return"["+o.join(",")+"]";
default:o=[];
for(q in u){if(b.call(u,q)){p=l(q,u);
if(p){o.push(h(q)+":"+p)
}}}return"{"+o.join(",")+"}"
}}})("",{"":j})
},checkCORSSupport:function(){if(a.util.browser.msie&&!window.XDomainRequest&&+a.util.browser.version.split(".")[0]<11){return true
}else{if(a.util.browser.opera&&+a.util.browser.version.split(".")<12){return true
}else{if(a.util.trim(navigator.userAgent).slice(0,16)==="KreaTVWebKit/531"){return true
}else{if(a.util.trim(navigator.userAgent).slice(-7).toLowerCase()==="kreatel"){return true
}}}}var h=navigator.userAgent.toLowerCase();
var i=h.indexOf("android")>-1;
if(i){return true
}return false
}};
d=a.util.now();
(function(){var i=navigator.userAgent.toLowerCase(),h=/(chrome)[ \/]([\w.]+)/.exec(i)||/(webkit)[ \/]([\w.]+)/.exec(i)||/(opera)(?:.*version|)[ \/]([\w.]+)/.exec(i)||/(msie) ([\w.]+)/.exec(i)||/(trident)(?:.*? rv:([\w.]+)|)/.exec(i)||i.indexOf("compatible")<0&&/(mozilla)(?:.*? rv:([\w.]+)|)/.exec(i)||[];
a.util.browser[h[1]||""]=true;
a.util.browser.version=h[2]||"0";
if(a.util.browser.trident){a.util.browser.msie=true
}if(a.util.browser.msie||(a.util.browser.mozilla&&+a.util.browser.version.split(".")[0]===1)){a.util.storage=false
}})();
a.util.on(window,"unload",function(h){a.unsubscribe()
});
a.util.on(window,"keypress",function(h){if(h.charCode===27||h.keyCode===27){if(h.preventDefault){h.preventDefault()
}}});
a.util.on(window,"offline",function(){if(g.length>0){var h=[].concat(g);
for(var k=0;
k<h.length;
k++){var j=h[k];
j.close();
clearTimeout(j.response.request.id);
if(j.heartbeatTimer){clearTimeout(j.heartbeatTimer)
}}}});
a.util.on(window,"online",function(){if(g.length>0){for(var h=0;
h<g.length;
h++){g[h].execute()
}}});
return a
}));