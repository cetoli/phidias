package br.ufrj.nce.labase.phidias;

import java.io.File;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.phidias.business.AttendantBusiness;
import br.ufrj.nce.labase.phidias.business.PatientBusiness;
import br.ufrj.nce.labase.phidias.business.StimulusBusiness;
import br.ufrj.nce.labase.phidias.business.WordBusiness;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.communication.bean.AttendantBean;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.PatientBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.Keyword;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;
import br.ufrj.nce.labase.phidias.persistence.model.WordClassification;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testeChamaRecuperaMovimentos();

	}
	
	
	private static void testeChamaRecuperaMovimentos() {
		EventBean event = new EventBean();
		event.setPhaseId(1);
		event.setSessionId(1);
		
		EventResponseBean response = (EventResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_MOVES_ACTION, event);
		System.out.println(response.isSuccess());
	}

	private static void testeChamaRegistraComentario() {
		CommentBean comentarioContainer = new CommentBean();
		comentarioContainer.setPhaseId(1);
		comentarioContainer.setSessionId(1);
		comentarioContainer.setCommentText("Teste de Descricao");

		CommunicationProtocol.execute(CommunicationProtocol.REGISTER_COMMENT_ACTION, comentarioContainer);
	}

	private static void testeChamaRegistraEvento() {
		EventBean eventoContainer = new EventBean();
		eventoContainer.setPhaseId(1);
		eventoContainer.setActionTypeId(EventBean.GIVE_UP_EVENT);
		eventoContainer.setSessionId(1);
		eventoContainer.setObject1("cachorro.gif");
		eventoContainer.setObject2("coelho.gif");
		eventoContainer.setValidMove(false);
		eventoContainer.setMoveTime(20);
		
		CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
	}

	private static void testeChamaRegistraSessao() {
		SessionBean sessaoContainer = new SessionBean();
		sessaoContainer.setAttendantId(1);
		sessaoContainer.setGameId(1);
		sessaoContainer.setPatientId("Xuxa");

		SessionResponseBean resposta = (SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_ACTION, sessaoContainer);
		System.out.println(resposta.getSessionId());
	}

	private static void testeChamaRegistraFimSessao() {
		SessionBean sessaoContainer = new SessionBean();
		sessaoContainer.setId(1);

		SessionResponseBean resposta = (SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_END_ACTION, sessaoContainer);
		System.out.println(resposta.isSuccess());
		System.out.println(resposta.getSessionId());
	}

	private static void testeReproduzMidi() {
		MidiSound midi = new MidiSound("C:\\Documents and Settings\\Owner\\My Documents\\Mestrado\\documentos\\workspace\\JavaSoundDemo\\audio\\trippygaia1.mid", false);
		midi.start();
		midi.stop();

	}

	private static void testeImportaArquivo() {
		String filePathBase = "C:\\tese\\CriaConto\\bancopalavras\\importador\\";
		File file = new File(filePathBase);
		String[] files = file.list();

		WordBusiness pb = new WordBusiness();
		File newLocation;
		for (String filePath : files) {
			pb.importWord(filePathBase + filePath);

			file = new File(filePathBase + filePath);
			newLocation = new File("C:\\tese\\CriaConto\\bancopalavras\\importado\\" + filePath);
			file.renameTo(newLocation);
		}
	}

	private static void testeInsereAplicador() {

		AttendantBean aplicador = new AttendantBean();
		aplicador.setName("Andre Moraes");
		aplicador.setInstitution("Universidade Federal Fluminense");
		aplicador.setProfession("Analista de Sistemas");

		AttendantBusiness apBus = new AttendantBusiness();
		apBus.registerAttendant(aplicador);

	}
	

	private static void testeInserePaciente() {

		PatientBean paciente = new PatientBean();
		paciente.setId("Xuxa");
		paciente.setName("Xuxa");
		paciente.setGender("M");
		paciente.setBirthday(new GregorianCalendar(1978, 2 - 1, 16));

		PatientBusiness pacBus = new PatientBusiness();
		pacBus.registerPatient(paciente);

	}

	private static void teste2() {
		WordClassification cp = new WordClassification();
		cp.setClassification("CENARIO");

		Keyword pc = new Keyword();
		pc.setDescription("CASTELO");

		cp.addKeyword(pc);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CriaContoPersist");

		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(cp);
		et.commit();

		em.close();

	}
	
	private static void testeRecuperaProximoEstimulo() {
		StimulusBean estimuloContainer = new StimulusBean();
		estimuloContainer.setPhaseId(1);
		estimuloContainer.setSessionId(1);

		StimulusResponseBean resposta = (StimulusResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, estimuloContainer);
		System.out.println(resposta.getStimulusTypeId());
	}

	private static void testeChamaRegistraEstimulo() {
		StimulusBean estimuloContainer = new StimulusBean();
		estimuloContainer.setPhaseId(1);
		estimuloContainer.setStimulusTypeId(StimulusBean.INVOCA_NPC);
		estimuloContainer.setSessionId(1);
		estimuloContainer.setStimulusText("OLÁ AMIGUINHO!");

		StimulusBusiness estimuloBusiness = new StimulusBusiness();
		SessionGamePhaseStimulusType  fj = estimuloBusiness.registerStimulus(estimuloContainer);
		System.out.println(fj.getId());
	}

	private static void testeChamaRecuperaProximoEstimulo() {
		StimulusBean estimuloContainer = new StimulusBean();
		estimuloContainer.setPhaseId(1);
		estimuloContainer.setSessionId(1);

		StimulusBusiness estimuloBusiness = new StimulusBusiness();
		SessionGamePhaseStimulusType fj = estimuloBusiness.getNextStimulus(estimuloContainer);
		System.out.println(fj.getId());
	}

}
