import java.util.Scanner;

public class segmentTree {

    static int[] A;
    static int[] tree;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        A = new int[n+1];
        tree = new int[2*n];

        for(int i = 1; i <= n; i++){
            A[i] = in.nextInt();
        }

        build(1,1,n);
        StringBuilder sb = new StringBuilder("");
        char c;
        for(int i = 0; i < q; i++){
            c = in.next().charAt(0);
            int a = in.nextInt();
            int b = in.nextInt();
            if(c == 'q'){
                sb.append(query(1,1,n,a,b)+"\n");
            }else {
                update(1,1,n,a,b);
            }
        }
        System.out.println(sb);
    }

    private static void build(int node, int start, int end){
        if(start == end){
            tree[node] = A[start];
        }else{
            int mid = (start + end) / 2;
            build(2*node,start,mid);
            build(2*node+1,mid+1,end);
            tree[node] = Math.min(tree[2*node],tree[2*node+1]);
        }
    }

    private static void update(int node, int start, int end,int index, int value){
        if(start == end){
            A[index] = value;
            tree[node] = value;
        }else {
            int mid = (start + end) / 2;
            if(index <= mid){
                update(2*node,start,mid,index,value);
            }else{
                update(2*node+1,mid+1,end,index,value);
            }
            tree[node] = Math.min(tree[2*node],tree[2*node+1]);
        }
    }

    private static int query(int node, int start, int end, int left, int right){
        if(left > end || right < start){
            return Integer.MAX_VALUE;
        }
        if(left <= start && right >= end){
            return tree[node];
        }
        int mid = (start + end) / 2;
        int p1 = query(2*node,start,mid,left,right);
        int p2 = query(2*node+1,mid+1,end,left,right);
        return Math.min(p1,p2);
    }
}
