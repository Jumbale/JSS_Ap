package org.example.dataTransferObject;

public class HttpAiResponse <AiResponse>{



        private AiResponse aiResponse;
        private int statusCode;
        public HttpAiResponse(AiResponse aiResponse,int statusCode){
            this.aiResponse=aiResponse;
            this.statusCode=statusCode;

        }


        public AiResponse getAiResponse(){
            return aiResponse;
        }

        public int getStatusCode(){
            return statusCode;
        }
    }


