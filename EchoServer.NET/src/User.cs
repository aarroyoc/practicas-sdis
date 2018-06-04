using System;
using System.Net.Sockets;
using System.Threading;
using System.Collections.Concurrent;

class User{
	private bool active = true;
	private Socket socket;
	private ILineText channel;
	private ConcurrentQueue<string> queue;
	public int Id {get;set;}
	public User(Socket socket, int id)
	{
		this.Id = id;
		this.socket = socket;
		this.queue = new ConcurrentQueue<string>();
		var threadUser = new Thread(new ThreadStart(RunUser));
		threadUser.Start();
		var threadPostMessage = new Thread(new ThreadStart(RunPostMessage));
		threadPostMessage.Start();
	}
	private void RunUser()
	{
		channel = new SocketLineText(socket);
		while(true){
			string data = channel.NextLine();
			if(data == "bye\r\n"){
				break;
			}
			data = data.Insert(0,$"{Id}> ");
			Console.Write($"MSG: {data}");
			EchoServer.NET.Program.Broadcast(data);
		}
		active = false;
		// remove ourselves from the ConcurrentBag
		socket.Shutdown(SocketShutdown.Both);
		socket.Close();
	}
	private void RunPostMessage()
	{
		while(true){
			lock(queue){
			while(queue.IsEmpty){
				Monitor.Wait(queue);
			}
			string data = null;
			if(queue.TryDequeue(out data) && active){
				channel.WriteLine(data);
			}
			}
			if(!active){
				return;
			}
		}
	}

	public void PostMessage(string msg)
	{
		if(!active){
			return;
		}
		Monitor.Enter(queue);
		queue.Enqueue(msg);
		Monitor.Pulse(queue);
		Monitor.Exit(queue);
	}

}
