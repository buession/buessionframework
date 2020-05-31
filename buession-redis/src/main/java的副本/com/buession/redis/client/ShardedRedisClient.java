/**
 * @author Yong.Teng
 */
public interface ShardedRedisClient extends RedisClient {

	@Override
	default Set<String> sInter(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Set<byte[]> sInter(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Long sInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Long sInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Set<String> sUnion(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Set<byte[]> sUnion(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Long sUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}

	@Override
	default Long sUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}

	@Override
	default Status sMove(final String source, final String destKey, final String member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Long zInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long bitOp(final Operation operation, final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.BITOP);
	}

	@Override
	default Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.BITOP);
	}

	@Override
	default Status pfMerge(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Status pfMerge(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Transaction multi(){
		throw new NotSupportedCommandException(ProtocolCommand.MULTI);
	}

	@Override
	default void exec(final Transaction transaction){
		throw new NotSupportedCommandException(ProtocolCommand.EXEC);
	}

	@Override
	default void discard(final Transaction transaction){
		throw new NotSupportedCommandException(ProtocolCommand.DISCARD);
	}

	@Override
	default void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	default void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	default void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	default void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	default Status watch(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.WATCH);
	}

	@Override
	default Status watch(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.WATCH);
	}

	@Override
	default Status unwatch(){
		throw new NotSupportedCommandException(ProtocolCommand.UNWATCH);
	}

	@Override
	default Long publish(final String channel, final String message){
		throw new NotSupportedCommandException(ProtocolCommand.PUBLISH);
	}

	@Override
	default Long publish(final byte[] channel, final byte[] message){
		throw new NotSupportedCommandException(ProtocolCommand.PUBLISH);
	}

	@Override
	default Status select(final int db){
		throw new NotSupportedCommandException(ProtocolCommand.SELECT);
	}

	@Override
	default Status swapdb(final int db1, final int db2){
		throw new NotSupportedCommandException(ProtocolCommand.SWAPDB);
	}

	@Override
	default Long dbSize(){
		throw new NotSupportedCommandException(ProtocolCommand.DBSIZE);
	}

	@Override
	default Status flushDb(){
		throw new NotSupportedCommandException(ProtocolCommand.FLUSHDB);
	}

	@Override
	default Status flushAll(){
		throw new NotSupportedCommandException(ProtocolCommand.FLUSHALL);
	}

	@Override
	default Object eval(final String script){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final String script, final String... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script, final byte[]... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final String script, final String[] keys, final String[] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object evalSha(final String digest){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final String digest, final String... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[]... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final String digest, final String[] keys, final String[] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default List<Boolean> scriptExists(final String... sha1){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
	}

	@Override
	default List<Boolean> scriptExists(final byte[]... sha1){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
	}

	@Override
	default String scriptLoad(final String script){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
	}

	@Override
	default byte[] scriptLoad(final byte[] script){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
	}

	@Override
	default Status scriptKill(){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_KILL);
	}

	@Override
	default Status scriptFlush(){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_FLUSH);
	}

	@Override
	default Status save(){
		throw new NotSupportedCommandException(ProtocolCommand.SAVE);
	}

	@Override
	default String bgSave(){
		throw new NotSupportedCommandException(ProtocolCommand.BGSAVE);
	}

	@Override
	default String bgRewriteAof(){
		throw new NotSupportedCommandException(ProtocolCommand.BGREWRITEAOF);
	}

	@Override
	default Long lastSave(){
		throw new NotSupportedCommandException(ProtocolCommand.LASTSAVE);
	}

	@Override
	default Status slaveOf(final String host, final int port){
		throw new NotSupportedCommandException(ProtocolCommand.SLAVEOF);
	}

	@Override
	default RoleInfo role(){
		throw new NotSupportedCommandException(ProtocolCommand.ROLE);
	}

	@Override
	default Status auth(final String password){
		throw new NotSupportedCommandException(ProtocolCommand.AUTH);
	}

	@Override
	default Status auth(final byte[] password){
		throw new NotSupportedCommandException(ProtocolCommand.AUTH);
	}

	@Override
	default Info info(final InfoSection section){
		throw new NotSupportedCommandException(ProtocolCommand.INFO);
	}

	@Override
	default Info info(){
		throw new NotSupportedCommandException(ProtocolCommand.INFO);
	}

	@Override
	default RedisServerTime time(){
		throw new NotSupportedCommandException(ProtocolCommand.TIME);
	}

	@Override
	default Status clientSetName(final String name){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
	}

	@Override
	default Status clientSetName(final byte[] name){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
	}

	@Override
	default String clientGetName(){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_GETNAME);
	}

	@Override
	default List<Client> clientList(){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_LIST);
	}

	@Override
	default Status clientKill(final String host, final int port){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_KILL);
	}

	@Override
	default Status quit(){
		throw new NotSupportedCommandException(ProtocolCommand.QUIT);
	}

	@Override
	default void shutdown(){
		throw new NotSupportedCommandException(ProtocolCommand.SHUTDOWN);
	}

	@Override
	default void shutdown(final boolean save){
		throw new NotSupportedCommandException(ProtocolCommand.SHUTDOWN);
	}

	@Override
	default Status configSet(final String parameter, final float value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final float value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final String parameter, final double value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final double value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final String parameter, final int value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final int value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final String parameter, final long value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final long value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final String parameter, final String value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final byte[] value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default List<String> configGet(final String parameter){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_GET);
	}

	@Override
	default List<byte[]> configGet(final byte[] parameter){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_GET);
	}

	@Override
	default Status configResetStat(){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	default Status configRewrite(){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_REWRITE);
	}

	@Override
	default Object sync(){
		throw new NotSupportedCommandException(ProtocolCommand.SYNC);
	}

	@Override
	default Object pSync(final String masterRunId, final int offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final byte[] masterRunId, final int offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final String masterRunId, final long offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final byte[] masterRunId, final long offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Status ping(){
		throw new NotSupportedCommandException(ProtocolCommand.PING);
	}

	@Override
	default Object slowLog(final SlowLogCommand command, final String... args){
		throw new NotSupportedCommandException(ProtocolCommand.SLOWLOG);
	}

	@Override
	default Object slowLog(final SlowLogCommand command, final byte[]... args){
		throw new NotSupportedCommandException(ProtocolCommand.SLOWLOG);
	}

	@Override
	default void monitor(final RedisMonitor redisMonitor){
		throw new NotSupportedCommandException(ProtocolCommand.MONITOR);
	}

	@Override
	default String debugObject(final String key){
		throw new NotSupportedCommandException(ProtocolCommand.DEBUG_OBJECT);
	}

	@Override
	default String debugSegfault(){
		throw new NotSupportedCommandException(ProtocolCommand.DEBUG_SEGFAULT);
	}

}
