import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * Created by fubar on 12/24/14.
 */
public class BotBot {
    public BotBot() {

    }

    public void connect(String nick, String server, String password, String[] channels) {
        // config
        Configuration.Builder config = new Configuration.Builder();
        config.setName(nick);
        config.setServerHostname(server);
        config.setServerPassword(password);
        config.addListener(new BotBotListener());
        for (String channel : channels) {
            config.addAutoJoinChannel(channel);
        }
        System.out.println(config.getAutoJoinChannels().toString());

        PircBotX bot = new PircBotX(config.buildConfiguration());

        // connect to server
        /*try {
            bot.startBot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }*/
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
