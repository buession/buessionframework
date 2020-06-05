/**
 * @author Yong.Teng
 */
public interface SimpleRedisClient extends RedisClient {

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

}
