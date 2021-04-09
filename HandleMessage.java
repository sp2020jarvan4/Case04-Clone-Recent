public class HandleMessage {

        private String userId;
        private String password;
    
        public SOAPSecurityHandler(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public String getUserId() {
                return userId;
        }

        @Override
        public boolean handleMessage(final SOAPMessageContext msgCtx) {

                // Indicator telling us which direction this message is going in
                final Boolean outInd = (Boolean) msgCtx
                        .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
                // Handler must only add security headers to outbound messages
                if (outInd.booleanValue()) {
                    try {
                        // Get the SOAP Envelope
                        final SOAPEnvelope envelope = msgCtx.getMessage().getSOAPPart()
                                .getEnvelope();
        
                        // Header may or may not exist yet
                        SOAPHeader header = envelope.getHeader();
                        if (header == null)
                            header = envelope.addHeader();
        
                        // Add WSS Usertoken Element Tree
                        final SOAPElement security = header.addChildElement("Security",
                                "wsse",
                                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                        final SOAPElement userToken = security
                                .addChildElement("UsernameToken", "wsse");
                        userToken.addChildElement("Username", "wsse")
                                .addTextNode(userId);
                        userToken.addChildElement("Password", "wsse").addAttribute(
                                new QName("Type"),
                                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText")
                                .addTextNode(password);
        
                    } catch (final Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return true;
            }
        
        }
}
