package models;

import java.util.HashMap;

public class TimeHandler {
	private HashMap<Participant, Times> timeHandler;

	public TimeHandler() {
		timeHandler = new HashMap<Participant, Times>();
	}


	public void addStart(Participant participant, String startTime) {
        if(timeHandler.get(participant) == null) {
            addParticipant(participant);
        }
		timeHandler.get(participant).setStart(startTime);
	}

	public String getStart(Participant participant) {
        if (timeHandler.get(participant) == null){
        return "--.--.--";
    }

        String start = timeHandler.get(participant).getStart();
 		return start;
	}

	public void addFinish(Participant participant, String finishTime) {
        if(timeHandler.get(participant) == null) {
            addParticipant(participant);
        }
		timeHandler.get(participant).setFinish(finishTime);
	}

	public String getFinish(Participant participant) {
        if(timeHandler.get(participant) == null) {
            return "--.--.--";
        }
        String finish = timeHandler.get(participant).getFinish();

        return finish;
	}


    private void addParticipant(Participant participant) {
        Times time = new Times();
        timeHandler.put(participant, time);
    }

}
