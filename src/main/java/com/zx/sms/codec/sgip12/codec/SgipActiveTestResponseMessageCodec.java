/**
 *
 */
package com.zx.sms.codec.sgip12.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

import com.zx.sms.codec.cmpp.msg.Message;
import com.zx.sms.codec.cmpp.packet.PacketType;
import com.zx.sms.codec.sgip12.msg.SgipActiveTestResponseMessage;
import com.zx.sms.codec.sgip12.packet.SgipPacketType;
import com.zx.sms.common.GlobalConstance;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipActiveTestResponseMessageCodec extends MessageToMessageCodec<Message, SgipActiveTestResponseMessage> {
	private PacketType packetType;

	public SgipActiveTestResponseMessageCodec() {
		this(SgipPacketType.ACTIVETESTRESPONSE);
	}

	public SgipActiveTestResponseMessageCodec(PacketType packetType) {
		this.packetType = packetType;
	}


	@Override
	protected void decode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		int commandId =  msg.getHeader().getCommandId();
		if(packetType.getCommandId() != commandId) {
			//不解析，交给下一个codec
			out.add(msg);
			return;
		}

		SgipActiveTestResponseMessage responseMessage = new SgipActiveTestResponseMessage(msg.getHeader());
		responseMessage.setTimestamp(msg.getTimestamp());
		out.add(responseMessage);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, SgipActiveTestResponseMessage msg, List<Object> out) throws Exception {
		msg.setBodyBuffer(GlobalConstance.emptyBytes);
		msg.getHeader().setBodyLength(msg.getBodyBuffer().length);
		out.add(msg);

	}

}
