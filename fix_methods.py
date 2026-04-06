#!/usr/bin/env python3
import re

file_path = "/Users/tengyong/Documents/htdocs/buession.com/buessionframework/buession-redis/src/main/java/io/lettuce/core/StandaloneRedisCommands.java"

with open(file_path, 'r', encoding='utf-8') as f:
    lines = f.readlines()

output_lines = []
i = 0
while i < len(lines):
    line = lines[i]
    output_lines.append(line)
    
    # Check if this is a method with empty implementation
    if '@Override' in line and i + 2 < len(lines):
        method_line = lines[i + 1]
        body_line = lines[i + 2] if i + 2 < len(lines) else ""
        return_line = lines[i + 3] if i + 3 < len(lines) else ""
        
        # Match methods that return empty values
        empty_patterns = [
            r'return\s+0L;',
            r'return\s+"";',
            r'return\s+null;',
            r'return\s+List\.of\(\);',
            r'return\s+Set\.of\(\);',
            r'return\s+Map\.of\(\);',
            r'return\s+new\s+byte\[0\];',
            r'return\s+0\.0;'
        ]
        
        is_empty_implementation = any(re.search(pattern, return_line) for pattern in empty_patterns)
        
        if is_empty_implementation and '{' in body_line:
            # Extract method name and parameters
            method_match = re.search(r'public\s+(?:[\w<>]+\s+)?(\w+)\(([^)]*)\)', method_line)
            if method_match:
                method_name = method_match.group(1)
                params_str = method_match.group(2).strip()
                
                # Extract parameter names
                param_names = []
                if params_str:
                    # Split by comma but handle generics
                    param_parts = re.split(r',(?![^<]*>)', params_str)
                    for part in param_parts:
                        part = part.strip()
                        if part and part != '...':
                            tokens = part.split()
                            if tokens:
                                last_token = tokens[-1]
                                if last_token == '...' and len(tokens) > 1:
                                    param_names.append(tokens[-2].replace('...', '') + '...')
                                elif last_token != '...':
                                    param_names.append(last_token)
                                else:
                                    param_names.append(last_token)
                
                param_names_str = ', '.join(param_names)
                
                # Find the closing brace
                j = i + 4
                while j < len(lines) and '}' not in lines[j]:
                    j += 1
                
                if j < len(lines):
                    # Replace the entire method body
                    new_method = f'\t\treturn delegate.{method_name}({param_names_str});\n'
                    # Keep @Override and method signature, replace body
                    output_lines = output_lines[:-3]  # Remove last 3 lines (method declaration + { + return)
                    output_lines.append(f'{method_line.rstrip()}\n')
                    output_lines.append(new_method)
                    output_lines.append('\t}\n')
                    i = j + 1  # Skip to after closing brace
                    continue
    
    i += 1

with open(file_path, 'w', encoding='utf-8') as f:
    f.writelines(output_lines)

print("File processed successfully!")
