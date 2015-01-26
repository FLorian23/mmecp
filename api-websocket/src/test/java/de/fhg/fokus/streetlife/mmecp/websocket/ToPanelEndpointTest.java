package de.fhg.fokus.streetlife.mmecp.websocket;

import org.jboss.arquillian.testng.Arquillian;

/**
 * Created by bdi on 03/11/14.
 */
public class ToPanelEndpointTest extends Arquillian {

//	private static CountDownLatch messageLatch;
//	private static final String SENT_MESSAGE = "Hello STREETLIFE";
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//	private Server server;
//
//	@Deployment
//	public static JavaArchive createDeployment() {
//		return ShrinkWrap.create(JavaArchive.class).addClass(SessionManager.class).addClass(MessagingUtils.class)
//				.addClass(ToPanelEndpoint.class).addClass(MMECPConfig.class).addClass(SystemConfiguration.class)
//				.addClass(MMECPConfigFactory.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
//
//	@BeforeTest(enabled = false)
//	public void init() {
//		server = new Server("localhost", 8025, "/api-websocket", null, ToPanelEndpoint.class);
//		try {
//			server.start();
//			LOG.info("Standalone websocket sever started...");
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Test(enabled = false)
//	public void testToPanelEndpointConnectionProgrammatic() throws Exception {
//		messageLatch = new CountDownLatch(1);
//
//		final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();
//
//		ClientManager client = ClientManager.createClient();
//		client.connectToServer(new Endpoint() {
//
//			@Override
//			public void onOpen(Session session, EndpointConfig config) {
//				session.addMessageHandler(new MessageHandler.Whole<String>() {
//
//					@Override
//					public void onMessage(String message) {
//						LOG.info("Received message: {}", message);
//						messageLatch.countDown();
//						Assert.assertTrue(message.startsWith("Welcome, this is your sessionId: "));
//					}
//				});
//				try {
//					session.getBasicRemote().sendText(SENT_MESSAGE);
//				} catch (IOException e) {
//					Assert.fail();
//					e.printStackTrace();
//				}
//			}
//		}, cec, new URI("ws://localhost:8025/api-websocket/panelui"));
//		messageLatch.await(100, TimeUnit.SECONDS);
//	}
//
//	// This is just to show how to start the annoted client! Please use annotated method for further implementation.
//	@Test(enabled = false)
//	public void testToPanelEndpointAnnotated() throws Exception {
//		messageLatch = new CountDownLatch(1);
//		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//		String uri = "ws://localhost:8080/api-websocket/panelui";
//		container.connectToServer(ClientEndpointTest.class, URI.create(uri));
//		messageLatch.await(40, TimeUnit.SECONDS);
//	}
//
//	@AfterTest(enabled = false)
//	public void afterClass() {
//		server.stop();
//		LOG.info("Standalone websocket sever stopped...");
//	}

}
