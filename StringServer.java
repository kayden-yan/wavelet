import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
   String display = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return display;
        } 
        else if (url.getPath().equals("/add-message")) {
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            if(parameters.length == 1){
                return "Input is empty";
            }
            else{
                display += parameters[1] + "\n";
                return display;
            }
        }
        else if (url.getPath().equals("/empty")){
            display = "";
            return "Cache cleaned";
        }
        else {
            
            return "404 Not Found";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
