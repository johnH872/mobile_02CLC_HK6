package hcmute.edu.fragmentcommunication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    //Bất kì thay đổi nào cũng sẽ ảnh kích hoạt callback
    //Cho ta biết được dữ liệu thay đổi của selectedItem
    private final MutableLiveData<String> selectedItem = new MutableLiveData<String>();

    public void  setData(String item) {
        selectedItem.setValue(item);
    }

    public LiveData<String> getSelectedItem() {
        return  selectedItem;
    }
}
