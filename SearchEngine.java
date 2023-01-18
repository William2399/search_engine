import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class Handler implements URLHandler {
    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String item = parameters[1];
                list.add(item);
                return String.format("String added!");
            }
            return "Not valid";
        } else if (url.getPath().equals("/")) {
                return list.toString();
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                String search_item = parameters[1];
                ArrayList<String> list_items = new ArrayList<>();
                if (parameters[0].equals("s")){
                    for (String item : list){
                        if (item.contains(search_item)) {
                            list_items.add(item);
                        }
                    }
                    return list_items.toString();
                }
            }
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