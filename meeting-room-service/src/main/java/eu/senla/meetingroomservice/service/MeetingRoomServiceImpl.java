package eu.senla.meetingroomservice.service;

//import eu.senla.meetingroomservice.entity.MeetingRoom;
//import eu.senla.meetingroomservice.repository.MeetingRoomRepository;
//import java.util.UUID;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MeetingRoomServiceImpl implements MeetingRoomService{
//
//    private final MeetingRoomRepository meetingRoomRepository;
//
//    @Override
//    public MeetingRoom findByNumber(Integer number) {
//
//        return meetingRoomRepository.findByNumber(number).get();
//        //todo exception
//    }
//
//
//
//    @Override
//    public MeetingRoom save(MeetingRoom meetingRoom) {
//
//        return meetingRoomRepository.save(meetingRoom);
//    }
//
//    @Override
//    public void add() {
//        MeetingRoom meetingRoom = new MeetingRoom();
//        meetingRoom.setId(UUID.fromString("f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95"));
//        meetingRoom.setNumber(1);
//        meetingRoomRepository.save(meetingRoom);
//    }
//}
