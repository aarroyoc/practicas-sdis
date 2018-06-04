using System.Net.Sockets;
using System.Text;

class SocketLineText : ILineText{
	private Socket socket;
	private byte[] bytes; 
	public SocketLineText(Socket socket){
		this.socket = socket;
	}
	public string NextLine()
	{
		string data = null;
		while(true){
			bytes = new byte[1024];
			int bytesRec = socket.Receive(bytes);
			data += Encoding.UTF8.GetString(bytes,0,bytesRec);
			if(data.IndexOf("\r") > -1){
				break;
			}
		}
		return data;
	}
	public void WriteLine(string line)
	{
		byte[] msg = Encoding.UTF8.GetBytes(line);
		socket.Send(msg);
	}
}