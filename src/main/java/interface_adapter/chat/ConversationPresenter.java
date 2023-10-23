//package interface_adapter.chat;
//
//import interface_adapter.ViewManagerModel;
//import use_case.chat.ConversationOutputBoundary;
//import use_case.signup.SignupOutputData;
//
//public class ConversationPresenter implements ConversationOutputBoundary {
//
//    private final ConversationViewModel conversationViewModel;
//    private ViewManagerModel viewManagerModel;
//
//    public ConversationPresenter(ViewManagerModel viewManagerModel, ConversationViewModel conversationViewModel) {
//        this.viewManagerModel = viewManagerModel;
//        this.conversationViewModel = conversationViewModel;
//    }
//
//    @Override
//    public void prepareSuccessView(SignupOutputData response) {
//
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//    }
//}
