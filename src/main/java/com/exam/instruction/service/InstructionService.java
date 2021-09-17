package com.exam.instruction.service;

import com.exam.instruction.exception.InstructionAlreadyExistsException;
import com.exam.instruction.exception.InstructionNotFoundException;
import com.exam.instruction.model.Instruction;
import com.exam.instruction.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;

@Service
public class InstructionService {

    @Autowired
    private InstructionRepository instructionRepository;

    public List<Instruction> getInstructions(){
        return instructionRepository.findAll();
    }

    public Instruction addInstruction(Instruction instruction) throws InstructionAlreadyExistsException {
        final boolean instructionExistWithContent = instructionRepository.instructionExistsByContent(instruction.getContent());

        if(instructionExistWithContent){
            throw new InstructionAlreadyExistsException(INSTRUCTION_ALREADY_EXISTS+instruction.getContent());
        }else{
            int newInstructionId = instructionRepository.addInstruction(instruction);
            instruction.setId(newInstructionId);
        }

        return instruction;
    }

    public Instruction getInstructionById(Integer instructionId) throws InstructionNotFoundException {
        final Instruction instruction = instructionRepository.findById(instructionId);
        if(instruction == null) {
            throw new InstructionNotFoundException(INSTRUCTION_NOT_FOUND_FOR_ID+instructionId);
        }
        return instruction;
    }

    public Instruction getInstructionByContent(String content) throws InstructionNotFoundException {
        final Instruction instruction = instructionRepository.findByContent(content);
        if(instruction == null) {
            throw new InstructionNotFoundException(INSTRUCTION_NOT_FOUND_FOR_CONTENT+content);
        }
        return instruction;
    }

    public boolean removeInstruction(Integer instructionId){
        return instructionRepository.delete(instructionId);
    }

    public boolean removeAllInstructions(){
        return instructionRepository.deleteAll();
    }

    public boolean removeInstructionsByIds(List<Integer> ids){
        return instructionRepository.deleteByIds(ids);
    }

    public boolean updateInstruction(Integer id, Instruction instruction){
        return instructionRepository.updateInstruction(id, instruction);
    }
}
