import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> searchList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
        	String[] params = url.getQuery().split("=");
        	if(params[0].equals("s")){
        		searchList.add(params[1]);
        		return String.format("Keyword: " + params[1]
        				+ " added successfully!");
        	}
            return String.format("Incorrect query.");
        } 
        else if (url.getPath().equals("/search")) {
        	String[] params = url.getQuery().split("=");
        	if(params[0].equals("s")){
        		String output = "Search result: ";
        		String searchWord = url.getQuery().split("=")[1];
        		boolean empty = true;
        		for(String key: searchList) {
        			if (key.contains(searchWord)) {
        				empty = false;
        				output += key + ", ";
        			}
        			
        			if (empty) {
        				return output + "No result!";
        			}
        			else {
        				return output.substring(0, output.length() - 2);
        			}
        		}
        		return String.format("Keyword: " + params[1]
        				+ " added successfully!");
        	}
            return String.format("Incorrect query.");
        } 
        else {
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
