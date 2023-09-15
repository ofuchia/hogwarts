package edu.tcu.cs.hogwartsartifactsonline.controller;

import java.util.List;
import edu.tcu.cs.hogwartsartifactsonline.domain.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.domain.Result;
import edu.tcu.cs.hogwartsartifactsonline.domain.StatusCode;
import edu.tcu.cs.hogwartsartifactsonline.service.ArtifactService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artifacts") //each method gets this as a prefix automatically
public class ArtifactController {

    private ArtifactService artifactService;
    //construction permits Spring to auto inject an instance of ArtfactService into the class
    //called auto wire
    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @GetMapping
    public Result findAll(){
        List<Artifact> all = artifactService.findAll();
        Result result = new Result(true, StatusCode.SUCCESS, "Find All Success", all);
        return result;

    }
    @GetMapping("/{artifactID}")
    public Result findById(@PathVariable String artifactId){
        return new Result(true, StatusCode.SUCCESS, "Find One Success", artifactService.findById(artifactId));

    }
    @PostMapping
    public Result save(@RequestBody Artifact newArtifact){ //spring MVC automaticaly retrieves and binds JSON object to java objecu
        //bc this is JSON, we need to use a reader to read the string and jaxson to de...it @2:12
        artifactService.save(newArtifact);
        return new Result(true, StatusCode.SUCCESS, "Save Success");

    }
    @PutMapping("/{artifactId}")
    public Result update(@PathVariable String artifactId, @RequestBody Artifact updatedArtifact){
        artifactService.update(artifactId, updatedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Update Success");

    }

    @DeleteMapping("/{artifactId}")
    public Result delete(@PathVariable String artifactId){
        artifactService.delete(artifactId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
