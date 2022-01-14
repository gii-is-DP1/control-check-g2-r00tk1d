package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedingService {

    private final FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    public List<Feeding> getAll(){
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.findFeedingTypeByName(typeName);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        if(p.getPet().getType() != p.getFeedingType().petType) {
            throw new UnfeasibleFeedingException();
        }
        return feedingRepository.save(p);
    }

    
}
