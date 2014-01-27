package models;

import java.util.HashMap;

public class TimeHandler {
	private HashMap<String, Times> timeHandler;

	public TimeHandler() {
		timeHandler = new HashMap<String, Times>();
	}


	public void addStart(Participant participant, String startTime) {
        if(timeHandler.get(participant.getId()) == null) {
            addParticipant(participant);
        }
		timeHandler.get(participant.getId()).setStart(startTime);
	}

	public String getStart(Participant participant) {
        if (timeHandler.get(participant.getId()) == null){
        return "--.--.--";
    }

        String start = timeHandler.get(participant.getId()).getStart();
 		return start;
	}

	public void addFinish(Participant participant, String finishTime) {
        if(timeHandler.get(participant.getId()) == null) {
            addParticipant(participant);
        }
		timeHandler.get(participant.getId()).setFinish(finishTime);
	}

	public String getFinish(Participant participant) {
        if(timeHandler.get(participant.getId()) == null) {
            return "--.--.--";
        }
        String finish = timeHandler.get(participant.getId()).getFinish();

        return finish;
	}


    private void addParticipant(Participant participant) {
        Times time = new Times();
        timeHandler.put(participant.getId(), time);
    }

}
