/**
 * @author Yong.Teng
 */
public class ShardedJedisClient extends AbstractJedisRedisClient<ShardedJedis> implements ShardedRedisClient {

	@Override
	public byte[] echo(final byte[] str){
		return execute(ProtocolCommand.ECHO, (ShardedJedis client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return object(command, SafeEncoder.encode(key));
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(ProtocolCommand.OBJECT, new Executor<ShardedJedis, Object>() {

			@Override
			public Object execute(ShardedJedis client){
				switch(command){
					case ENCODING:
						return client.objectEncoding(key);
					case IDLETIME:
						return client.objectIdletime(key);
					case REFCOUNT:
						return client.objectRefcount(key);
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("key", key));
	}

}
