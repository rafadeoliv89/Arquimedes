package br.arquimedes.controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.twilio.rest.api.v2010.account.Message;

import br.arquimedes.util.TwilioWhatsApp;

@WebServlet("/envio/whatsapp")
public class EnvioWhatsapp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EnvioWhatsapp() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String numeroCezar = "+5511998586009";//cezar
		String numeroAbelino = "+5511974653995";//abelino
		String numero = "+5511973456656";
		String mensagem = "Aqui é o Arquimedes dizendo que existe uma nova fórmula disponível em seu módulo de Matemática Financeira. Faça bom proveito!";
		try {
			Message msg = TwilioWhatsApp.enviar(null, numero,
					mensagem);
			JSONPObject json = new JSONPObject("twilioResposta", msg);
			out.print(json.getValue().toString());
		} catch (Exception e) {
			out.print("Falha ao enviar!!!");
			e.printStackTrace();
		}

		out.close();
	}

}

