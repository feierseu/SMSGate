package com.zx.sms.handler.sgip;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.zx.sms.codec.sgip12.msg.SgipActiveTestResponseMessage;

public class SgipActiveTestResponseMessageHandler extends SimpleChannelInboundHandler<SgipActiveTestResponseMessage>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, SgipActiveTestResponseMessage msg) throws Exception {
		ctx.channel().close();
	}}