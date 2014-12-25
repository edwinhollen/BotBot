import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;

/**
 * Created by fubar on 12/24/14.
 */
public class BotBot {
    private ArrayList<String> channels;

    public BotBot() {
        channels = new ArrayList<>();
    }

    public void connect(String nick, String server, String password, ArrayList<String> channels) {
        // config
        Configuration config = new Configuration.Builder()
                .setName(nick)
                .setServerHostname(server)
                .setServerPassword(password)
                .addAutoJoinChannel("ok")
                .addListener(new BotBotListener())
                .buildConfiguration();

        System.out.println(config.getAutoJoinChannels().toString());
        PircBotX bot = new PircBotX(config);

        // connect to server
        /*try {
            bot.startBot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }*/
    }

    public void addChannel(String c) {
        channels.add(c);
    }

    public void removeChannel(String c) {
        channels.remove(c);
    }

    private class BotBotListener extends ListenerAdapter {
        @Override
        public void onGenericMessage(GenericMessageEvent event) {
            // when someone says hello, respond with hello world
            if (event.getMessage().startsWith("?helloworld")) {
                event.respond("Hello world!");
            }
        }
    }
}
