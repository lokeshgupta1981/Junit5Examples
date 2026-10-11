package com.howtodoinjava.junit.backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class BackupService {

  public Path createArchive(Path directory, String name) throws IOException {
    Path archive = directory.resolve(name + ".tar");
    Files.createFile(archive);
    return archive;
  }

  public Path createPrivateArchive(Path directory, String name) throws IOException {
    Path archive = createArchive(directory, name);
    Set<PosixFilePermission> ownerOnly = PosixFilePermissions.fromString("rw-------");
    Files.setPosixFilePermissions(archive, ownerOnly);
    return archive;
  }

  public String target() {
    return System.getProperty("backup.target", "local");
  }
}
