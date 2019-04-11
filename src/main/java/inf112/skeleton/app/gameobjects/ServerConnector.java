package inf112.skeleton.app.gameobjects;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.ArrayList;

public class ServerConnector {
    Client client;

    public ServerConnector() {

        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(inf112.skeleton.app.cards.ProgramCard.class);
        kryo.register(inf112.skeleton.app.cards.Program.class);
        kryo.register(inf112.skeleton.app.SomeClass.class);
        kryo.register(java.util.ArrayList.class);






    }

    public void connect(){
        client.start();

        try {
            client.connect(5000,"127.0.0.1",65342);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendOBJECT(Object object){
        client.sendTCP(object);
    }
}
