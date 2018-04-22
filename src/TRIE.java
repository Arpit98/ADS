import java.util.HashMap;
import java.util.Scanner;

public class TRIE {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();

        StringBuilder stringBuilder;
        int weight;
        TrieNode trieNode = new TrieNode(new HashMap<>(),false,0);

        for(int i = 0; i < n; i++){
            stringBuilder = new StringBuilder(in.next());
            weight = in.nextInt();
            insert(trieNode,stringBuilder,weight);
        }

        for(int i = 0; i < q; i++){
            stringBuilder = new StringBuilder(in.next());
            System.out.println(maxWeight(trieNode,stringBuilder));
        }
    }

    private static void insert(TrieNode trieNode,StringBuilder stringBuilder, int weight){

        if(stringBuilder.length() > 0){
            char c = stringBuilder.charAt(0);
            stringBuilder.deleteCharAt(0);

            if(trieNode.hashMap.containsKey(c)){
                if(weight > trieNode.weight){
                    trieNode.weight = weight;
                }
                insert(trieNode.hashMap.get(c),stringBuilder,weight);
            }else{
                if(weight > trieNode.weight){
                    trieNode.weight = weight;
                }
                TrieNode trieNode1 = new TrieNode(new HashMap<>(),false,weight);
                trieNode.hashMap.put(c,trieNode1);
                trieNode.isEndOfWord = false;
                insert(trieNode1,stringBuilder,weight);
            }
        }else{
            trieNode.isEndOfWord = true;
            trieNode.weight = weight;
        }
    }

    private static int maxWeight(TrieNode trieNode,StringBuilder stringBuilder){
        if(stringBuilder.length() > 0){
            char c = stringBuilder.charAt(0);
            stringBuilder.deleteCharAt(0);
            if(trieNode.hashMap.containsKey(c)){
                return maxWeight(trieNode.hashMap.get(c),stringBuilder);
            }else{
                return -1;
            }
        }else{
            return trieNode.weight;
        }
    }

    static class TrieNode{
        HashMap<Character,TrieNode> hashMap;
        boolean isEndOfWord;
        int weight;

        TrieNode(HashMap<Character,TrieNode> hashMap, boolean isEndOfWord, int weight){
            this.hashMap = hashMap;
            this.isEndOfWord = isEndOfWord;
            this.weight = weight;
        }
    }
}
