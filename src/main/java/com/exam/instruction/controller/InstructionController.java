package com.exam.instruction.controller;

import com.exam.instruction.exception.InstructionAlreadyExistsException;
import com.exam.instruction.exception.InstructionNotFoundException;
import com.exam.instruction.model.Instruction;
import com.exam.instruction.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructions")
@CrossOrigin("*")
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/")
    public List<Instruction> getInstructions(){
        return instructionService.getInstructions();
    }

    @PostMapping("/")
    public Instruction addInstruction(@RequestBody Instruction instruction) throws InstructionAlreadyExistsException {
        return instructionService.addInstruction(instruction, 1);
    }

    @GetMapping("/{id}")
    public Instruction getInstructionById(@PathVariable("id") Integer instructionId) throws InstructionNotFoundException {
        return instructionService.getInstructionById(instructionId);
    }

    @DeleteMapping("/{id}")
    public boolean removeInstruction(@PathVariable("id") Integer instructionId){
        return instructionService.removeInstruction(instructionId);
    }

    @PutMapping("/{id}")
    public boolean updateInstruction(@RequestBody Instruction instruction, @PathVariable("id") Integer id){
        return instructionService.updateInstruction(id, instruction, 1);
    }
}
