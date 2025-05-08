package com.proj.kvcore.wal;

import com.proj.kvcore.command.Command;

import java.io.IOException;

public interface WriteAheadLog {
    void append(Command command) throws IOException;
    void replay(CommandApplier applier) throws IOException;

    interface CommandApplier {
        void apply(Command command);
    }
}
