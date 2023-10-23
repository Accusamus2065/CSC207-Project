//package use_case.chat;
//
//
//import entity.people.Patient;
//
//public class ConversationInteractor implements ConversationInputBoundary {
//    final IConversationDAO dao;
//    final ConversationOutputBoundary presenter;
//
//    public ConversationInteractor(IConversationDAO convDao,
//                                  ConversationOutputBoundary presenter) {
//        this.convDao = convDao;
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void execute(ConversationInputData data) {
//        // sender and receiver share the same Conversation object
//        Patient sender = (Patient) data.getMessage().getSender();       // TODO: change this Patient to general Users
//        Patient receiver = (Patient) data.getMessage().getReceiver();
//        if (sender.getConversation() == receiver.getConversation()) {   //
//
//        }
//
//
//        ConversationOutputData signupOutputData = new ConversationOutputData(user.getUsername(), now.toString(), false);
//        userPresenter.prepareSuccessView(signupOutputData);
//
//    }
//}