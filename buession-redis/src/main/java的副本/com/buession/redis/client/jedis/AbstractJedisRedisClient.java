/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<T extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<T> {

	@Override
	public String echo(final String str){
		return execute(ProtocolCommand.ECHO, (T client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

}
