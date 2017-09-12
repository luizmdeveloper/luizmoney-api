package com.luizmoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("luizmoney")
public class LuizMoneyApiProperty {

	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class  Seguranca {
		
		private boolean enabledHttps;
		
		private String orgiemPermitida = "http://localhost:8000";

		public boolean isEnabledHttps() {
			return enabledHttps;
		}

		public void setEnabledHttps(boolean enabledHttps) {
			this.enabledHttps = enabledHttps;
		}

		public String getOrgiemPermitida() {
			return orgiemPermitida;
		}

		public void setOrgiemPermitida(String orgiemPermitida) {
			this.orgiemPermitida = orgiemPermitida;
		}		
	}
}
