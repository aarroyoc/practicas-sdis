using System;
using System.Net;
using System.Net.Sockets;
using System.Collections.Concurrent;

namespace EchoServer.NET
{
    class Program
    {
		static ConcurrentBag<User> users;
		public static void Broadcast(string msg){
			foreach(var user in users){
				user.PostMessage(msg);
			}
		}
        static void Main(string[] args)
        {
            byte[] bytes = new Byte[1024];
			Socket server = new Socket(AddressFamily.InterNetwork,SocketType.Stream,ProtocolType.Tcp);
			IPEndPoint localEndPoint = new IPEndPoint(IPAddress.Any, 4444);
			server.Bind(localEndPoint);
			server.Listen(10);
			users = new ConcurrentBag<User>();
			Console.WriteLine("Servidor escuchando en 4444");
			
			int id = 0;
			while(true){
				Socket handler = server.Accept();
				var user = new User(handler,id);
				id++;
				users.Add(user);
			}
        }
    }
}