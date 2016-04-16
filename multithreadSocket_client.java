import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class multithreadSocket_client
{
	public static void main(String args[])
	{
		try{
			Socket cs=new Socket("localhost",7878);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PrintStream out=new PrintStream(cs.getOutputStream());
			String msg;
			while(true)
			{
				msg=br.readLine();
				out.println(msg);
				if(msg.equals("bye"))
				{
					System.out.println("Client is offline.");
					break;
				}
			}
			out.close();
			cs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
