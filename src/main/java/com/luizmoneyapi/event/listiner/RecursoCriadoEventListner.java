package com.luizmoneyapi.event.listiner;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luizmoneyapi.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoEventListner implements ApplicationListener<RecursoCriadoEvent>  {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {	
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();
		
		aidiconarHeaderLocation(response,codigo);
	}

	private void aidiconarHeaderLocation(HttpServletResponse response,Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("location", uri.toASCIIString());
	}

}
