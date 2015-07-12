import java.io.*;
import java.net.*;

class multithreadSocket_client
{
	public static void main(String args[])
	{
		try{
			Socket cs=new Socket("localhost",7878);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PrintStream out=new PrintStream(cs.getOutputStream());
			//BufferedReader in=new BufferedReader(new InputStreamReader(cs.getInputStream()));
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
			in.close();
			out.close();
			cs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
