using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading.Tasks;

namespace EchoServerAsync.NET
{
    class Program
    {
        static void Main(string[] args)
        {
            new Server().Start().Wait();
        }
    }

    class Server {
        public async Task Start(){
            TcpListener server = new TcpListener(IPAddress.Any,5555);
            server.Start();
            Console.WriteLine("Server running at 5555");
            int worker, io;
            System.Threading.ThreadPool.GetMaxThreads(out worker,out io);
            Console.WriteLine($"Threads: {worker}\tI/O: {io}");
            System.Threading.ThreadPool.GetAvailableThreads(out worker,out io);
            Console.WriteLine($"Threads: {worker}\tI/O: {io}");
            while(true){
                var socket = await server.AcceptTcpClientAsync();
                await Task.Run(()=>{
                    HandleClient(socket);
                });
            }
        }

        public async void HandleClient(TcpClient client){
            bool quit = false;
            while(!quit){
                var stream = client.GetStream();
                StreamReader reader = new StreamReader(stream);
                StreamWriter writer = new StreamWriter(stream);
                var line = await reader.ReadLineAsync();

                if(line == "bye"){
                    quit = true;
                }
                Console.WriteLine($"ECHO: {line}");
                await writer.WriteLineAsync(line);
                await writer.FlushAsync();
            }
            client.Close();
        }
    }
}
