package edu.tcu.cs.hogwartsartifactsonline.service;

import edu.tcu.cs.hogwartsartifactsonline.domain.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.utils.IdWorker;
import edu.tcu.cs.hogwartsartifactsonline.dao.ArtifactDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional //all or nothing execution
public class ArtifactService {

    private ArtifactDao artifactDao;
    private IdWorker idWorker;

    //Spring will automatically inject an instance of ArtifactDao and IdWorker into
    public ArtifactService(ArtifactDao artifactDao, IdWorker idWorker) {
        this.artifactDao = artifactDao;
        this.idWorker = idWorker;
    }

    public List<Artifact> findAll(){
        return artifactDao.findAll();
    }

    public Artifact findById(String artifactId){
        return artifactDao.findById(artifactId).get();
    }

    public void save(Artifact newArtifact){
        newArtifact.setId(idWorker.nextId() + "");
        artifactDao.save(newArtifact);
    }

    public void update(String artifactId, Artifact updatedArtifact) {
        updatedArtifact.setId(artifactId);
        artifactDao.save(updatedArtifact);  //Hibernate knows new (sql update) vs brandnew (sql insert)
    }

    public void delete(String artifactId) {
        artifactDao.deleteById(artifactId);
    }
}
