package br.ufrj.nce.labase.common;

import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MidiSound implements MetaEventListener, Runnable {

	private Sequencer sequencer = null;
	private Sequence currentSound;
	private boolean midiEOM;
	private Thread thread;
	private boolean replay;
	
	
	/**
	 * @param filePath
	 * @param replay
	 */
	public MidiSound(String filePath, boolean replay) {
		init(replay);

		try {
			currentSound = MidiSystem.getSequence(MidiSound.class.getResourceAsStream(filePath));
		} catch (InvalidMidiDataException imde) {
			throw new RuntimeException("Unsupported audio file.", imde);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @param inputStream
	 * @param replay
	 */
	public MidiSound(InputStream inputStream, boolean replay) {
		init(replay);

		try {
			currentSound = MidiSystem.getSequence(inputStream);
		} catch (InvalidMidiDataException imde) {
			throw new RuntimeException("Unsupported audio file.", imde);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void init(boolean replay) {
		try {
			this.replay = replay;
			sequencer = MidiSystem.getSequencer();
			sequencer.addMetaEventListener(this);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}

	private void play() {

		try {
			sequencer.open();
			sequencer.setSequence((Sequence) currentSound);

		} catch (InvalidMidiDataException imde) {
			throw new RuntimeException("Unsupported audio file.", imde);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		sequencer.start();

		while (!midiEOM) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				break;
			}
		}

		if (sequencer.isOpen()) {
			sequencer.stop();
		}
		
		sequencer.close();

		if (!replay)
			this.stop();
		else
			midiEOM = false;

	}

	public void start() {
		thread = new Thread(this);
		thread.setName("Midi");
		thread.start();
	}

	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
		thread = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sound.midi.MetaEventListener#meta(javax.sound.midi.MetaMessage)
	 */
	public void meta(MetaMessage message) {
		if (message.getType() == 47) { // 47 is end of track
			midiEOM = true;
		}
	}

	public void run() {
		do {
			this.play();
		} while (replay && thread != null);
	}

}
