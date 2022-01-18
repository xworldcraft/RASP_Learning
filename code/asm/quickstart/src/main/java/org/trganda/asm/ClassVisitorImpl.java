package org.trganda.asm;

import org.objectweb.asm.*;

import java.io.IOException;

public class ClassVisitorImpl extends ClassVisitor {

    public ClassVisitorImpl() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("    " + descriptor + " " + name);
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("    " + name + " " + descriptor);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException {
        ClassVisitorImpl cv = new ClassVisitorImpl();
        // loading the class file of java.lang.Runnable and travel it with method override in ClassVisitorImpl
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cv, 0);
    }
}
