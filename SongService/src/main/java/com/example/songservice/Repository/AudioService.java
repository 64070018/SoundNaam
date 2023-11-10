import com.example.songservice.POJO.Audio;
import com.example.songservice.Repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioService {

    private final AudioRepository audioRepository;

    @Autowired
    public AudioService(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    public List<Audio> getAllAudio() {
        try {
            return audioRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
