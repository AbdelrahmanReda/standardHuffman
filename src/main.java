import java.util.ArrayList;
import java.util.List;

public class main {
    public static boolean founded=false;
    
    public static ArrayList<Node> introduceNewLine (ArrayList <Node> input){
        ArrayList <Node> newLine = new ArrayList<Node>();
        for (int i = 0; i <input.size()-2 ; i++) {
            Node node = new Node();
            node=input.get(i);
            newLine.add(node);
        }
        Node node = new Node();
        System.out.println(input.size()-2);
        node.proibability=input.get(input.size()-2).proibability+input.get(input.size()-1).proibability;
        node.symbol=input.get(input.size()-2).symbol+"+"+input.get(input.size()-1).symbol;
        newLine.add(node);
    
      
        
        return newLine;
        
        
    }
    
    public static ArrayList<Node> orderList (ArrayList <Node> input){
       
        for (int i = 1; i <input.size() ; i++) {
            for (int j = 0; j <input.size()-1 ; j++) {
                
                if (input.get(j).proibability<input.get(j+1).proibability)
                {
                    Node fNode = input.get(j);
                    Node sNode = input.get(j+1);
                   input.set(j,sNode);
                    input.set(j+1,fNode);
                }
            }
        }
    
    
        return input;
    
    }
    
    public static void main (String args[])
    {
    
        ArrayList <Character> charecters = new ArrayList<Character>();
        ArrayList <Double> repeatation = new ArrayList<Double>();
        String message = "abaacaadaa" ;
        for (int i = 0; i <message.length() ; i++) {
            Double counter = 1.0;
            for (int j = i+1; j <message.length() ; j++) {
                if (message.charAt(i)==message.charAt(j))
                counter++;
            }
            if (!charecters.contains(message.charAt(i)))
            {
                charecters.add(message.charAt(i));
                repeatation.add(counter);
                
            }
            
            counter=1.0;
        }
        ArrayList <Node> allNodes = new ArrayList<Node>();
    
        for (int i = 0; i <charecters.size() ; i++){
            Node node = new Node();
            node.symbol=Character.toString(charecters.get(i));
            node.proibability=repeatation.get(i)/message.length();
            allNodes.add(node);
        }
        
        ArrayList<ArrayList<Node>> aList = new ArrayList<ArrayList<Node> >();
        aList.add(orderList(allNodes));
    
        for (int i = 0; i <charecters.size()-2 ; i++) {
            aList.add(orderList(introduceNewLine(aList.get(i))));
        }
        aList.get(aList.size()-1).get(0).code="0";
        aList.get(aList.size()-1).get(1).code="1";
        
        
        
        for (int i = aList.size() -1; i >=0 ; i--) {
            for (int j = 0; j <aList.get(i).size() ; j++) {
                
                
                if (i-1 >=0)
                {
                    
                    for (int k = 0; k<aList.get(i-1).size()  ; k++) {
    
                        if (aList.get(i).get(j).symbol.equals(aList.get(i-1).get(k).symbol))
                        {
                            aList.get(i-1).get(k).code=aList.get(i).get(j).code;
                        }
                        else if (aList.get(i).get(j).symbol.contains(aList.get(i-1).get(k).symbol) && founded==false)
                        {
                            founded=true;
                            aList.get(i-1).get(k).code=aList.get(i).get(j).code+"0";
                        }
                        else if (aList.get(i).get(j).symbol.contains(aList.get(i-1).get(k).symbol) && founded==true)
                        {
    
                            founded=false;
                            aList.get(i-1).get(k).code=aList.get(i).get(j).code+"1";
                        }
                        
                    }
                    
                }
               
    
    
    
            }
        }
    
    
                for (int i = 0; i <aList.size() ; i++) {
                    for (int j = 0; j < aList.get(i).size(); j++) {
                        System.out.println(aList.get(i).get(j).symbol + " " + aList.get(i).get(j).proibability + " " + aList.get(i).get(j).code);
                    }
                    System.out.println("@@@@@@@@@@");
                }
    
        for (int i = 0; i <message.length() ; i++) {
            for (int j = 0; j <aList.get(0).size() ; j++) {
                if (Character.toString(message.charAt(i)).equals(aList.get(0).get(j).symbol))
                {
                   // System.out.println("yes");
                    System.out.print(aList.get(0).get(j).code);
                }
            }
        }
    
    }
    
    
}
