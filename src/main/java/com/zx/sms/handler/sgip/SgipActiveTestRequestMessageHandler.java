package com.zx.sms.handler.sgip;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

import com.zx.sms.codec.sgip12.msg.SgipActiveTestRequestMessage;
import com.zx.sms.codec.sgip12.msg.SgipActiveTestResponseMessage;
@Sharable
public class SgipActiveTestRequestMessageHandler extends SimpleChannelInboundHandler<SgipActiveTestRequestMessage>{

	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, SgipActiveTestRequestMessage msg) throws Exception {
		SgipActiveTestResponseMessage resp = new SgipActiveTestResponseMessage(msg.getHeader());
		ChannelFuture future = ctx.channel().writeAndFlush(resp);
		final ChannelHandlerContext finalctx = ctx;
		future.addListeners(new GenericFutureListener(){
			@Override
			public void operationComplete(Future future) throws Exception {
				ctx.executor().schedule(new Runnable(){

					@Override
					public void run() {
						finalctx.channel().close();
					}


				},500,TimeUnit.MILLISECONDS);
			}
		});


	}}