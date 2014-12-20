
public class Priority {
	
	
	 public int size;
     private Dlnode header,tail;
     int value,key;
     Priority(){
         header =new Dlnode();
         tail=new Dlnode();
         header.setnext(tail);
         tail.setprev(header);
         size=0;
       }
       public int getsize(){
           return(size);
       }
       public boolean isEmpty(){
           if(size==0)
               return true;
           else
               return false;
       }
       public int min()throws ListEmptyException{
           if(size==0)
               throw new ListEmptyException();
           Dlnode q=header.getnext().getnext();int min_key=header.getnext().getkey();int min_value=header.getnext().getvalue();
           while(q.getnext()!=null){
               if(q.getkey()<min_key){
                   min_value=q.getvalue();
               }
               q=q.getnext();
           }
           return(min_key);
       }
       public Dlnode removeMin()throws ListEmptyException{
            if(size==0)
               throw new ListEmptyException();
            Dlnode q=header.getnext();
            int min_key=header.getnext().getkey();
            int min_value=header.getnext().getvalue();
            Dlnode temp=q;
            while(q.getnext()!=null){
                if(q.getkey()<min_key){
                   min_value=q.getvalue();
                   min_key=q.getkey();
                   temp=q;
               }
               q=q.getnext();
           }
           
           temp.getprev().setnext(temp.getnext());
           temp.getnext().setprev(temp.getprev());
           size--;
            
           return(temp);
           
       }
       public void insert(int v,int k,Dlnode temp){
           Dlnode p=tail.getprev();
           //temp=new Dlnode(p,v,k,tail);
           temp.data=v;
           temp.key=k;
           temp.next=tail;
           temp.prev=p;
           p.setnext(temp);
           tail.setprev(temp);
           size++;
       }
       public void insert(int v,int k){
           Dlnode p=tail.getprev();
           Dlnode q=new Dlnode(p,v,k,tail);
           p.setnext(q);
           tail.setprev(q);
           size++;
       }
       public void display(){
           Dlnode q=header.getnext();
           while(q.getnext()!=null){
               System.out.println(q.getvalue()+"\t"+q.getkey());
               q=q.getnext();
           }
           System.out.println();
       }

}
class Dlnode
{
	Dlnode next;//for priority queue
	Dlnode prev;
	Dlnode left;//for generating tree
	Dlnode right;
	int data;
	int key;

	Dlnode()
	{
		prev=next=null;
		left=right=null;
		data=key=0;
	}
	Dlnode(Dlnode p,int val,int k,Dlnode n)
	{
		prev=p;
		data=val;
		key=k;
		next=n;
		
	}

	void setData(int v)
	{
		data = v;
	}

	void setnext(Dlnode n)
	{
		next = n;
	}

	int getvalue()
	{
		return data;
	}
	int getkey()
	{
		return key;
	}

	Dlnode getnext()
	{
		return next;
	}

	void setprev(Dlnode n)
	{
	prev = n;
	}

	Dlnode getprev()
	{
		return prev;
	}

}
