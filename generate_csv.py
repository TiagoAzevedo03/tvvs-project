import os
from pathlib import Path

def generate_test_pairs_csv(project_root, project_name="CrossingGuardJoe"):
    # Define source and test directories
    src_dir = os.path.join(project_root, "src", "main", "java", project_name)
    test_dir = os.path.join(project_root, "src", "test", "java", project_name)

    # Store results
    pairs = []

    # Walk through the source directory
    for root, _, files in os.walk(src_dir):
        for file in files:
            if file.endswith(".java") and not file.endswith("Test.java"):
                # Get full path of source file
                src_file = os.path.join(root, file)

                # Construct the expected test file path
                relative_path = os.path.relpath(root, src_dir)
                test_file_name = os.path.splitext(file)[0] + "Test.java"
                test_file = os.path.join(test_dir, relative_path, test_file_name)

                # Only add if test file exists
                if os.path.exists(test_file):
                    # Convert to absolute paths
                    src_file_abs = os.path.abspath(src_file)
                    test_file_abs = os.path.abspath(test_file)
                    pairs.append((project_name, src_file_abs, test_file_abs))

    # Write to CSV file
    output_file = "tsdetector-input.csv"
    with open(output_file, "w") as f:
        for project, src, test in pairs:
            f.write(f"{project},{src},{test}\n")

    print(f"Generated {output_file} with {len(pairs)} test pairs")

# Usage example:
if __name__ == "__main__":
    # Replace this with your project root directory
    project_root = "/C:/Users/Utilizador/Desktop/FEUP/MESW/project-crossing-guard-joe"
    generate_test_pairs_csv(project_root)