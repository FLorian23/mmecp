package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.websocket.DeploymentException;

import com.google.gwt.core.client.JsonUtils;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;
import de.fhg.fokus.streetlife.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.client.model.MapContent;

/**
 * Created by bdi on 07/11/14.
 */
@Startup
public class WebSocketController {

	@Inject
	@MMECPConfig(value = "mmecp.backend.api.websocket.url")
	private String mmecpWebSocketBaseUrl;

	@Inject
	@MMECPConfig(value = "mmecp.backend.api.websocket.endpoint.panelui")
	private String mmecpWebSocketEndPoint;

    @Inject
    private MapContent mapContent;

	@PostConstruct
	public void init() throws IOException, DeploymentException {
		Websocket socket = new Websocket(mmecpWebSocketBaseUrl + mmecpWebSocketEndPoint);
        socket.addListener(new WebsocketListener() {

            @Override
            public void onClose() {
                // do something on close
            }

            @Override
            public void onMessage(String msg) {
                if (JsonUtils.safeToEval(msg)) {
                    mapContent.setObjectJson(msg);
                }
            }

            @Override
            public void onOpen() {
                socket.send("Hello MMECP, I'm the PanelUi!");
            }
        });
	}
}
