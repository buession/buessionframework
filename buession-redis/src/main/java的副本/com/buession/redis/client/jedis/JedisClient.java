/**
 * @author Yong.Teng
 */
public class JedisClient extends AbstractJedisRedisClient<Jedis> implements SimpleRedisClient {


	@Override
	public Status save(){
		return execute(ProtocolCommand.SAVE, (Jedis client)->ReturnUtils.returnForOK(client.save()));
	}

	@Override
	public String bgSave(){
		return execute(ProtocolCommand.BGSAVE, (Jedis client)->client.bgsave());
	}

	@Override
	public String bgRewriteAof(){
		return execute(ProtocolCommand.BGREWRITEAOF, (Jedis client)->client.bgrewriteaof());
	}

	@Override
	public Long lastSave(){
		return execute(ProtocolCommand.LASTSAVE, (Jedis client)->client.lastsave());
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return execute(ProtocolCommand.SLAVEOF, (Jedis client)->ReturnUtils.returnForOK(client.slaveof(host, port)),
				OperationsCommandArguments.getInstance().put("host", host).put("port", port));
	}

	@Override
	public Status auth(final String password){
		return execute(ProtocolCommand.AUTH, (Jedis client)->ReturnUtils.returnForOK(client.auth(password)),
				OperationsCommandArguments.getInstance().put("password", password));
	}

	@Override
	public Status auth(final byte[] password){
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public Info info(final InfoSection section){
		return execute(ProtocolCommand.INFO,
				(Jedis client)->InfoUtil.convert(client.info(section.name().toLowerCase())),
				OperationsCommandArguments.getInstance().put("section", section));
	}

	@Override
	public Info info(){
		return execute(ProtocolCommand.INFO, (Jedis client)->InfoUtil.convert(client.info()));
	}

	@Override
	public RedisServerTime time(){
		return execute(ProtocolCommand.TIME, (Jedis client)->ReturnUtils.returnRedisServerTime(client.time()));
	}

	@Override
	public Status clientSetName(final String name){
		return execute(ProtocolCommand.CLIENT_SETNAME,
				(Jedis client)->ReturnUtils.returnForOK(client.clientSetname(name)),
				OperationsCommandArguments.getInstance().put("name", name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return execute(ProtocolCommand.CLIENT_SETNAME,
				(Jedis client)->ReturnUtils.returnForOK(client.clientSetname(name)),
				OperationsCommandArguments.getInstance().put("name", name));
	}

	@Override
	public String clientGetName(){
		return execute(ProtocolCommand.CLIENT_GETNAME, (Jedis client)->client.clientGetname());
	}

	@Override
	public List<Client> clientList(){
		return execute(ProtocolCommand.CLIENT_LIST, (Jedis client)->ClientUtil.parse(client.clientList()));
	}

	@Override
	public Status clientKill(final String host, final int port){
		return execute(ProtocolCommand.CLIENT_KILL, (Jedis client)->ReturnUtils.returnForOK(client.clientKill(host +
				":" + port)), OperationsCommandArguments.getInstance().put("host", host).put("port", port));
	}

	@Override
	public Status quit(){
		return execute(ProtocolCommand.QUIT, (Jedis client)->ReturnUtils.returnForOK(client.quit()));
	}

	@Override
	public void shutdown(){
		execute(ProtocolCommand.SHUTDOWN, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.shutdown();
				return null;
			}

		});
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return configSet(parameter, Float.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return configSet(parameter, NumberUtils.float2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return configSet(parameter, Double.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return configSet(parameter, NumberUtils.double2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return configSet(parameter, Integer.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return configSet(parameter, NumberUtils.int2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return configSet(parameter, Long.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return configSet(parameter, NumberUtils.long2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return execute(ProtocolCommand.CONFIG_SET, (Jedis client)->ReturnUtils.returnForOK(client.configSet(parameter,
				value)), OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return execute(ProtocolCommand.CONFIG_SET, (Jedis client)->ReturnUtils.returnForOK(client.configSet(parameter,
				value)), OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
	}

	@Override
	public List<String> configGet(final String parameter){
		return execute(ProtocolCommand.CONFIG_GET, (Jedis client)->client.configGet(parameter),
				OperationsCommandArguments.getInstance().put("parameter", parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return execute(ProtocolCommand.CONFIG_GET, (Jedis client)->client.configGet(parameter),
				OperationsCommandArguments.getInstance().put("parameter", parameter));
	}

	@Override
	public Status configResetStat(){
		return execute(ProtocolCommand.CONFIG_RESETSTAT,
				(Jedis client)->ReturnUtils.returnForOK(client.configResetStat()));
	}

	@Override
	public Status configRewrite(){
		return execute(ProtocolCommand.CONFIG_REWRITE,
				(Jedis client)->ReturnUtils.returnForOK(client.configRewrite()));
	}

	@Override
	public Object sync(){
		return execute(ProtocolCommand.SYNC, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.sync();
				return null;
			}

		});
	}

	@Override
	public byte[] echo(byte[] str){
		return execute(ProtocolCommand.ECHO, (Jedis client)->client.echo(str),
				OperationsCommandArguments.getInstance().put("str", str));
	}

	@Override
	public Status ping(){
		return execute(ProtocolCommand.PING,
				(Jedis client)->ReturnUtils.returnStatus("PONG".equalsIgnoreCase(client.ping())));
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
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

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
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

	@Override
	public Object slowLog(final SlowLogCommand command, final String... args){
		return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case GET:
						return client.slowlogGet();
					case LEN:
						return client.slowlogLen();
					case RESET:
						return client.slowlogReset();
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("args", args));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... args){
		return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

			@Override
			public Object execute(Jedis client){
				switch(command){
					case GET:
						return client.slowlogGet();
					case LEN:
						return client.slowlogLen();
					case RESET:
						return client.slowlogReset();
					default:
						return null;
				}
			}

		}, OperationsCommandArguments.getInstance().put("command", command).put("args", args));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		execute(ProtocolCommand.MONITOR, new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis client){
				client.monitor(new JedisMonitor() {

					@Override
					public void onCommand(final String command){
						redisMonitor.onCommand(command);
					}
				});

				return null;
			}

		});
	}

	@Override
	public String debugObject(final String key){
		return execute(ProtocolCommand.DEBUG_OBJECT, (Jedis client)->client.debug(DebugParams.OBJECT(key)));
	}

	@Override
	public String debugSegfault(){
		return execute(ProtocolCommand.DEBUG_SEGFAULT, (Jedis client)->client.debug(DebugParams.SEGFAULT()));
	}

}
