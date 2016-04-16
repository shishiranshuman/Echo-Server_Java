import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class multithreadSocket implements Runnable
{
	Socket csocket;
	static int i=0;
	multithreadSocket(Socket csocket)
	{
		this.csocket=csocket;
	}
	public void run()
	{
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(csocket.getInputStream()));
			String msg;
			while(true)
			{
				msg=in.readLine();
				System.out.println("client["+csocket+"] says "+msg);
				if(msg.equals("bye"))
				{
					i--;
					System.out.println("Total clients connected: "+i);
					if(i==0)
					{
						System.out.println("Server is offline.");
						System.exit(0);
					}
					else
						break;
				}
			}
			in.close();
			csocket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	} 
	public static void main(String args[])
	{
		try{
			ServerSocket ssock=new ServerSocket(7878);
			System.out.println("Listening:");
			while(true)
			{
				Socket sock=ssock.accept();
				i++;
				System.out.println("Connected "+sock);
				System.out.println("Total clients connected: "+i);
				Thread t=new Thread(new multithreadSocket(sock));
				t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
