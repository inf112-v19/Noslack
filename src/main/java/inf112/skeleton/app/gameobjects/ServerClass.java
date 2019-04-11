package inf112.skeleton.app.gameobjects;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.SomeClass;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.Robots.Player;

import java.io.IOException;
import java.util.ArrayList;

public class ServerClass {
    Server server;

    public ServerClass(){
        this.server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(inf112.skeleton.app.cards.ProgramCard.class);
        kryo.register(inf112.skeleton.app.cards.Program.class);
        kryo.register(inf112.skeleton.app.SomeClass.class);
        kryo.register(java.util.ArrayList.class);





    }

    public void start(){

        System.out.println("Server starting");
        server.start();
        try {
            server.bind(65342);
        } catch (IOException e) {
            e.printStackTrace();
        }



        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                System.out.println(".");
                if (object instanceof String) {
                    System.out.println("ITS A PLAYER");

                    System.out.println(object);

                    String response = "Thanks";
                    connection.sendTCP(response);
                }
                else if(object instanceof SomeClass){
                    System.out.println("ITS A CARD!");
                }

                else if(object instanceof ArrayList){
                    System.out.println("ITS A ARRAYLIST!");

                    System.out.println(((ArrayList) object).size());
                }

            }
        });




    }
}


